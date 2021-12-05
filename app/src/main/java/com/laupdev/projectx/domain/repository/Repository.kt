package com.laupdev.projectx.domain.repository

import com.laupdev.projectx.data.database.Hotel
import com.laupdev.projectx.domain.workers.database.IDatabaseWorker
import com.laupdev.projectx.domain.workers.firestore.IHotelWorker
import com.laupdev.projectx.domain.workers.storage.IStorageWorker

class Repository(
    private val databaseWorker: IDatabaseWorker,
    private val hotelWorker: IHotelWorker,
    private val storageWorker: IStorageWorker
    ): IRepository {

    override suspend fun getLoggedInUser() = databaseWorker.getLoggedInUser()

    override suspend fun getUserByEmail(email: String) = databaseWorker.getUserByEmail(email)

    override suspend fun getHotelsPaging(fromId: Long, limit: Long) = hotelWorker.getHotelsUsingPaging(fromId, limit)
//        hotelWorker.getHotelsUsingPaging(fromId, limit)?.let { querySnapshot ->
//            querySnapshot.documents.forEach { document ->
//                val hotelId = document.getLong("id")
//                val name = document.getString("name")
//                val description = document.getString("description")
//                val mainImageUrl = document.getString("main_image_url")
//                if (hotelId != null &&
//                    name != null &&
//                    description != null &&
//                    mainImageUrl != null) {
//                    val mainImagePath = storageWorker.savePictureToInternalStorage(name, mainImageUrl)
//                    val newHotel = Hotel(
//                        hotelId,
//                        name,
//                        description,
//                        mainImagePath
//                    )
//                    databaseWorker.insertHotel(newHotel)
//                    document.getField<Map<String, String>>("contact_info")?.let { contactInfo ->
//                        val address = contactInfo["address"]
//                        val contacts = contactInfo["contacts"]
//                        if (address != null && contacts != null) {
//                            databaseWorker.insertContactInfo(ContactInfo(
//                                hotelId = hotelId,
//                                address = address,
//                                contacts = contacts
//                            ))
//                        }
//                    }
//                    var pictureDocuments: List<DocumentSnapshot>? = null
//                    document.reference.collection("pictures")
//                        .get()
//                        .addOnCompleteListener {
//                            if (it.isSuccessful) {
//                                pictureDocuments = it.result?.documents
//                            }
//                        }
//                        .await()
//                    pictureDocuments?.let {
//                        val pictures = mutableListOf<Picture>()
//                        it.forEach { pictureDoc ->
//                            val imageTitle = pictureDoc.getString("image_title")
//                            val imageUrl = pictureDoc.getString("image_url")
//                            if (imageTitle != null && imageUrl != null) {
//                                val imagePath = storageWorker.savePictureToInternalStorage(imageTitle, imageUrl)
//                                pictures.add(Picture(
//                                    hotel_id = hotelId,
//                                    imageTitle = imageTitle,
//                                    imagePath = imagePath
//                                ))
//                            }
//                        }
//                        databaseWorker.insertPictures(pictures)
//                    }
//                }
//            }
//        }

    override suspend fun getHotelById(hotelId: Long) = hotelWorker.getHotelById(hotelId)

    override suspend fun getPicturesByHotelId(hotelId: Long) = hotelWorker.getPicturesByHotelId(hotelId)

    override suspend fun getContactInfoByHotelId(hotelId: Long) = hotelWorker.getContactInfoByHotelId(hotelId)

    override suspend fun handleUserAuthData(email: String, password: String) = databaseWorker.handleUserAuthData(email, password)

    override suspend fun updateUserIsLoggedIn(userId: Int, isLoggedIn: Int) = databaseWorker.updateUserIsLoggedIn(userId, isLoggedIn)

    override suspend fun signOut() = databaseWorker.signOut()

    override fun populateFirestore() = hotelWorker.populateFirestore()
}