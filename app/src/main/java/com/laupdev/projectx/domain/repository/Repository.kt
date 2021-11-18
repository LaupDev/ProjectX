package com.laupdev.projectx.domain.repository

import com.laupdev.projectx.domain.workers.database.IDatabaseWorker
import com.laupdev.projectx.domain.workers.firestore.IFirestoreWorker

class Repository(
    private val databaseWorker: IDatabaseWorker,
    private val firestoreWorker: IFirestoreWorker
    ): IRepository {

    override suspend fun getLoggedInUser() = databaseWorker.getLoggedInUser()

    override suspend fun getUserByEmail(email: String) = databaseWorker.getUserByEmail(email)

    override suspend fun getHotelsPaging(fromId: Int, size: Int) = databaseWorker.getHotelsPaging(fromId, size)

    override suspend fun getHotelById(hotelId: Int) = databaseWorker.getHotelById(hotelId)

    override suspend fun getPicturesByHotelId(hotelId: Int) = databaseWorker.getPicturesByHotelId(hotelId)

    override suspend fun getContactInfoByHotelId(hotelId: Int) = databaseWorker.getContactInfoByHotelId(hotelId)

    override suspend fun handleUserAuthData(email: String, password: String) = databaseWorker.handleUserAuthData(email, password)

    override suspend fun updateUserIsLoggedIn(userId: Int, isLoggedIn: Int) = databaseWorker.updateUserIsLoggedIn(userId, isLoggedIn)

    override suspend fun signOut() = databaseWorker.signOut()

    override fun populateFirestore() = firestoreWorker.populateFirestore()
}