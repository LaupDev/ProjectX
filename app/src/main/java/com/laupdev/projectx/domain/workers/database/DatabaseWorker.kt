package com.laupdev.projectx.domain.workers.database

import com.laupdev.projectx.data.database.*

class DatabaseWorker(private val userDao: UserDao, private val hotelDao: HotelDao) : IDatabaseWorker {
    override suspend fun getUserByUsername(username: String) = userDao.getUserByUsername(username)

    override suspend fun getUserByEmail(email: String) = userDao.getUserByEmail(email)

    override suspend fun getHotelsPaging(fromId: Int, size: Int) = hotelDao.getHotelsPaging(fromId, size)

    override suspend fun getHotelById(hotelId: Int) = hotelDao.getHotelById(hotelId)

    override suspend fun getPicturesByHotelId(hotelId: Int) = hotelDao.getPicturesByHotelId(hotelId)

    override suspend fun getContactInfoByHotelId(hotelId: Int) = hotelDao.getContactsByHotelId(hotelId)
}