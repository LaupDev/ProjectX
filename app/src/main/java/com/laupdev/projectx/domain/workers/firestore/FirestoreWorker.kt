package com.laupdev.projectx.domain.workers.firestore

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import timber.log.Timber

class FirestoreWorker : IFirestoreWorker {
    private val firestore = Firebase.firestore

    override fun populateFirestore() {
        for (i in 0..15) {
            val hotelData = hashMapOf(
                "name" to "Hotel $i",
                "description" to "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                "main_image_path" to "https://foto.hrsstatic.com/fotos/0/2/1280/1280/100/FFFFFF/http%3A%2F%2Ffoto-origin.hrsstatic.com%2Ffoto%2F0%2F0%2F7%2F9%2F007970%2F007970_ha_28855911.jpg/rRuicPT0oQIyfSaLs35T2g%3D%3D/382%2C500/6/ANTALYA_HOTEL_RESORT_AND_SPA-Antalya-Hotel_outdoor_area-8-7970.jpg",
                "contact_info" to mapOf(
                    "address" to "262 East Railroad St.Painesville, OH 44077",
                    "contacts" to "Ofra Torunn Altansarnai: +421992349421|Regena Borghildr Olympias: +421992416352"
                )
            )
            firestore.collection("hotels")
                .add(hotelData).addOnSuccessListener {
                    it.collection("pictures").add(
                        hashMapOf(
                            "image_title" to "Picture 1",
                            "image_path" to "https://images.unsplash.com/photo-1573052905904-34ad8c27f0cc?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8aG90ZWwlMjBpbnRlcmlvcnxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80"
                        )
                    )
                    it.collection("pictures").add(
                        hashMapOf(
                            "image_title" to "Picture 2",
                            "image_path" to "https://images.unsplash.com/photo-1563911302283-d2bc129e7570?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8aG90ZWx8ZW58MHx8MHx8&ixlib=rb-1.2.1&w=1000&q=80"
                        )
                    )
                    it.collection("pictures").add(
                        hashMapOf(
                            "image_title" to "Picture 3",
                            "image_path" to "https://images.unsplash.com/photo-1605346434674-a440ca4dc4c0?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fGhvdGVsJTIwcm9vbXxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80"
                        )
                    )
                    it.collection("pictures").add(
                        hashMapOf(
                            "image_title" to "Picture 4",
                            "image_path" to "https://www.hotel-grandior.cz/files-sbbasic/ba_grandiorprague_cz/new_grandior_lobby_portrait.jpg?w=800&h=952"
                        )
                    )
                }
                .addOnFailureListener {
                    Timber.d(it, "Firestore population failed")
                }
        }
    }
}