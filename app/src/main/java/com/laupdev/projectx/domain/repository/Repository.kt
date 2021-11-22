package com.laupdev.projectx.domain.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.getField
import com.laupdev.projectx.data.database.ContactInfo
import com.laupdev.projectx.data.database.Hotel
import com.laupdev.projectx.domain.workers.database.IDatabaseWorker
import com.laupdev.projectx.domain.workers.firestore.IFirestoreWorker
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class Repository(
    private val databaseWorker: IDatabaseWorker,
    private val firestoreWorker: IFirestoreWorker
    ): IRepository {

    override suspend fun getLoggedInUser() = databaseWorker.getLoggedInUser()

    override suspend fun getUserByEmail(email: String) = databaseWorker.getUserByEmail(email)

    override suspend fun getHotelsPaging(fromId: Long, limit: Long): List<Hotel> {
        firestoreWorker.getHotelsUsingPaging(fromId, limit)?.let { querySnapshot ->
            querySnapshot.documents.forEach { document ->
                document.getLong("id")?.let { hotelId ->
                    document.getString("name")?.let { name ->
                        document.getString("description")?.let { description ->
                            document.getString("main_image_path")?.let { imagePath ->
                                val newHotel = Hotel(
                                    hotelId,
                                    name,
                                    description,
                                    imagePath
                                )
                                databaseWorker.insertHotel(newHotel)
                                document.getField<Map<String, String>>("contact_info")?.let { contactInfo ->
                                    contactInfo["address"]?.let { address ->
                                        contactInfo["contacts"]?.let { contacts ->
                                            databaseWorker.insertContactInfo(ContactInfo(
                                                hotelId = hotelId,
                                                address = address,
                                                contacts = contacts
                                            ))
                                        }
                                    }
                                }
                                var pictureDocuments: List<DocumentSnapshot>? = null
                                document.reference.collection("pictures")
                                    .get()
                                    .addOnCompleteListener {
                                        if (it.isSuccessful) {
                                            pictureDocuments = it.result?.documents
                                        }
                                    }
                                    .await()
                                pictureDocuments?.let {
                                    it.forEach { pictureDoc ->
                                        pictureDoc.getString("image_title")?.let { imageTitle ->
                                            pictureDoc.getString("image_path")?.let { imagePath ->
                                                // TODO: 22.11.2021 Save pictures to database and storage
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return databaseWorker.getHotelsPaging(fromId, limit)
    }

    override suspend fun getHotelById(hotelId: Int) = databaseWorker.getHotelById(hotelId)

    override suspend fun getPicturesByHotelId(hotelId: Int) = databaseWorker.getPicturesByHotelId(hotelId)

    override suspend fun getContactInfoByHotelId(hotelId: Int) = databaseWorker.getContactInfoByHotelId(hotelId)

    override suspend fun handleUserAuthData(email: String, password: String) = databaseWorker.handleUserAuthData(email, password)

    override suspend fun updateUserIsLoggedIn(userId: Int, isLoggedIn: Int) = databaseWorker.updateUserIsLoggedIn(userId, isLoggedIn)

    override suspend fun signOut() = databaseWorker.signOut()

    override fun populateFirestore() = firestoreWorker.populateFirestore()
}