package com.laupdev.projectx.domain.workers.firestore

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import com.laupdev.projectx.data.database.ContactInfo
import com.laupdev.projectx.data.database.Hotel
import com.laupdev.projectx.data.database.Picture
import com.laupdev.projectx.domain.workers.database.IDatabaseWorker
import com.laupdev.projectx.domain.workers.storage.IStorageWorker
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class HotelWorker(
    private val databaseWorker: IDatabaseWorker,
    private val storageWorker: IStorageWorker
) : IHotelWorker {

    private val firestore = Firebase.firestore

    // TODO: 24.11.2021 Add update functionality

    override suspend fun getHotelsUsingPaging(fromId: Long, limit: Long): List<Hotel> {
        var result: QuerySnapshot? = null
        firestore.collection("hotels")
            .orderBy("id")
            .startAfter(fromId)
            .limit(limit)
            .get()
            .addOnCompleteListener {
                result = if (it.isSuccessful) {
                    it.result
                } else {
                    null
                }
            }
            .await()
        result?.let {
            it.documents.forEach { document ->
                val hotelId = document.getLong("id")
                val name = document.getString("name")
                val description = document.getString("description")
                val mainImageUrl = document.getString("main_image_url")
                if (hotelId != null &&
                    name != null &&
                    description != null &&
                    mainImageUrl != null) {
                    val mainImagePath =
                        storageWorker.savePictureToInternalStorage(name, mainImageUrl)
                    val newHotel = Hotel(
                        hotelId,
                        name,
                        description,
                        mainImagePath
                    )
                    databaseWorker.insertHotel(newHotel)
                }
            }
        }
        return databaseWorker.getHotelsPaging(fromId, limit)
    }

    override suspend fun getHotelById(hotelId: Long): Hotel {
//        var result: DocumentSnapshot? = null
//        firestore.collection("hotels")
//            .whereEqualTo("id", hotelId)
//            .get()
//            .addOnCompleteListener {
//                if (it.isSuccessful) {
//                    result = it.result?.documents?.get(0)
//                }
//            }
//            .await()
//        result?.let { document ->
//            val name = document.getString("name")
//            val description = document.getString("description")
//            val mainImageUrl = document.getString("main_image_url")
//            if (name != null &&
//                description != null &&
//                mainImageUrl != null
//            ) {
//                val mainImagePath = storageWorker.savePictureToInternalStorage(name, mainImageUrl)
//                val newHotel = Hotel(
//                    hotelId,
//                    name,
//                    description,
//                    mainImagePath
//                )
//                databaseWorker.insertHotel(newHotel)
//            }
//        }
        return databaseWorker.getHotelById(hotelId)
    }

    override suspend fun getContactInfoByHotelId(hotelId: Long): ContactInfo {
        var result: DocumentSnapshot? = null
        firestore.collection("hotels")
            .whereEqualTo("id", hotelId)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    result = it.result?.documents?.get(0)
                }
            }
            .await()
        result?.let { document ->
            val contactInfo = document.get("contact_info") as Map<String, String>?
            if (contactInfo != null) {
                val address = contactInfo["address"]
                val contacts = contactInfo["contacts"]
                if (address != null && contacts != null) {
                    databaseWorker.insertContactInfo(ContactInfo(
                        hotelId = hotelId,
                        address = address.toString(),
                        contacts = contacts.toString()
                    ))
                }
            }


        }
        return databaseWorker.getContactInfoByHotelId(hotelId)
    }

    override suspend fun getPicturesByHotelId(hotelId: Long): List<Picture> {
        var pictureDocuments: List<DocumentSnapshot>? = null
        var hotelReference: DocumentReference? = null
        firestore.collection("hotels")
            .whereEqualTo("id", hotelId)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    hotelReference = it.result?.documents?.get(0)?.reference
                }
            }
            .await()
        hotelReference?.let { reference ->
            reference.collection("pictures")
                .get()
                .addOnCompleteListener { picturesQuery ->
                    if (picturesQuery.isSuccessful) {
                        pictureDocuments = picturesQuery.result?.documents
                    }
                }
                .await()
        }

        pictureDocuments?.let {
            val pictures = mutableListOf<Picture>()
            it.forEach { pictureDoc ->
                val pictureId = pictureDoc.getLong("id")
                val imageTitle = pictureDoc.getString("image_title")
                val imageUrl = pictureDoc.getString("image_url")
                if (pictureId != null && imageTitle != null && imageUrl != null) {
                    val imagePath = storageWorker.savePictureToInternalStorage(imageTitle, imageUrl)
                    pictures.add(
                        Picture(
                            id = pictureId,
                            hotel_id = hotelId,
                            imageTitle = imageTitle,
                            imagePath = imagePath
                        )
                    )
                }
            }
            databaseWorker.insertPictures(pictures)
        }
        return databaseWorker.getPicturesByHotelId(hotelId)
    }

    override fun populateFirestore() {
        for (i in 1..15) {
            val hotelData = hashMapOf(
                "id" to i,
                "name" to "Hotel $i",
                "description" to "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "main_image_url" to "https://foto.hrsstatic.com/fotos/0/2/1280/1280/100/FFFFFF/http%3A%2F%2Ffoto-origin.hrsstatic.com%2Ffoto%2F0%2F0%2F7%2F9%2F007970%2F007970_ha_28855911.jpg/rRuicPT0oQIyfSaLs35T2g%3D%3D/382%2C500/6/ANTALYA_HOTEL_RESORT_AND_SPA-Antalya-Hotel_outdoor_area-8-7970.jpg",
                "contact_info" to mapOf(
                    "address" to "262 East Railroad St.Painesville, OH 44077",
                    "contacts" to "Ofra Torunn Altansarnai: +421992349421|Regena Borghildr Olympias: +421992416352"
                )
            )
            firestore.collection("hotels")
                .add(hotelData).addOnSuccessListener {
                    it.collection("pictures").add(
                        hashMapOf(
                            "id" to i*4-3,
                            "image_title" to "Picture 1",
                            "image_url" to "https://images.unsplash.com/photo-1573052905904-34ad8c27f0cc?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8aG90ZWwlMjBpbnRlcmlvcnxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80"
                        )
                    )
                    it.collection("pictures").add(
                        hashMapOf(
                            "id" to i*4-2,
                            "image_title" to "Picture 2",
                            "image_url" to "https://images.unsplash.com/photo-1563911302283-d2bc129e7570?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8aG90ZWx8ZW58MHx8MHx8&ixlib=rb-1.2.1&w=1000&q=80"
                        )
                    )
                    it.collection("pictures").add(
                        hashMapOf(
                            "id" to i*4-1,
                            "image_title" to "Picture 3",
                            "image_url" to "https://images.unsplash.com/photo-1605346434674-a440ca4dc4c0?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fGhvdGVsJTIwcm9vbXxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80"
                        )
                    )
                    it.collection("pictures").add(
                        hashMapOf(
                            "id" to i*4-0,
                            "image_title" to "Picture 4",
                            "image_url" to "https://www.hotel-grandior.cz/files-sbbasic/ba_grandiorprague_cz/new_grandior_lobby_portrait.jpg?w=800&h=952"
                        )
                    )
                }
                .addOnFailureListener {
                    Timber.d(it, "Firestore population failed")
                }
        }
    }
}