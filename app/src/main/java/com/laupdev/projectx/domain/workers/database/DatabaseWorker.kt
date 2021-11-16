package com.laupdev.projectx.domain.workers.database

import com.laupdev.projectx.data.database.*

class DatabaseWorker(private val userDao: UserDao, private val hotelDao: HotelDao) : IDatabaseWorker {

    override suspend fun getLoggedInUser() = userDao.getLoggedInUser()

    override suspend fun getUserByEmail(email: String) = userDao.getUserByEmail(email)

    override suspend fun getHotelsPaging(fromId: Int, size: Int) = hotelDao.getHotelsPaging(fromId, size)

    override suspend fun getHotelById(hotelId: Int) = hotelDao.getHotelById(hotelId)

    override suspend fun getPicturesByHotelId(hotelId: Int) = hotelDao.getPicturesByHotelId(hotelId)

    override suspend fun getContactInfoByHotelId(hotelId: Int) = hotelDao.getContactsByHotelId(hotelId)

    override suspend fun handleUserAuthData(email: String, password: String) {
        val user = userDao.getUserByEmail(email)
        val isAlreadyAdded = user != null
        if (isAlreadyAdded) {
            userDao.updateUserIsLoggedIn(UserIsLoggedIn(user!!.id, 1))
        } else {
            userDao.insertUser(
                User(
                    email = email,
                    password = password,
                    isLoggedIn = 1
                )
            )
        }
    }

    override suspend fun updateUserIsLoggedIn(userId: Int, isLoggedIn: Int) {
        userDao.updateUserIsLoggedIn(UserIsLoggedIn(userId, isLoggedIn))
    }

    override suspend fun signOut() {
        userDao.getLoggedInUser()?.let { user ->
            userDao.updateUserIsLoggedIn(UserIsLoggedIn(user.id, 0))
        }
    }
}