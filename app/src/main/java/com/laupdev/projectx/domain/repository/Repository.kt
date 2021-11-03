package com.laupdev.projectx.domain.repository

import com.laupdev.projectx.data.database.Hotel
import com.laupdev.projectx.data.database.HotelDao
import com.laupdev.projectx.data.database.UserDao

class Repository(private val userDao: UserDao, private val hotelDao: HotelDao): IRepository {

    override suspend fun getUserByUsername(username: String) = userDao.getUserByUsername(username)

    override suspend fun getUserByEmail(email: String) = userDao.getUserByEmail(email)

    override suspend fun getHotelsPaging(fromId: Int, size: Int) = hotelDao.getHotelsPaging(fromId, size)

    override suspend fun getHotelWithAllInfo(hotelId: Int): Hotel = hotelDao.getHotelWithAllInfoById(hotelId)

}