package com.laupdev.projectx.domain.workers.database

import com.laupdev.projectx.data.database.*

class DatabaseWorker(private val userDao: UserDao, private val hotelDao: HotelDao) : IDatabaseWorker {

    override suspend fun getLoggedInUser() = userDao.getLoggedInUser()

    override suspend fun getUserByEmail(email: String) = userDao.getUserByEmail(email)

    override suspend fun getHotelsPaging(fromId: Long, limit: Long) = hotelDao.getHotelsPaging(fromId, limit)

    override suspend fun getHotelById(hotelId: Long) = hotelDao.getHotelById(hotelId)

    override suspend fun getPicturesByHotelId(hotelId: Long) = hotelDao.getPicturesByHotelId(hotelId)

    override suspend fun getContactInfoByHotelId(hotelId: Long) = hotelDao.getContactsByHotelId(hotelId)

    override suspend fun insertHotel(hotel: Hotel): Long {
        return hotelDao.insertHotel(hotel)
    }

    override suspend fun insertContactInfo(contactInfo: ContactInfo) {
        hotelDao.insertContactInfo(contactInfo)
    }

    override suspend fun insertPictures(pictures: List<Picture>) {
        pictures.forEach { picture ->
            hotelDao.insertPicture(picture)
        }
    }

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