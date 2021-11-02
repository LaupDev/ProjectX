package com.laupdev.projectx.model

import com.laupdev.projectx.database.Hotel
import com.laupdev.projectx.database.HotelDao
import timber.log.Timber

class HotelsRepository(private val hotelDao: HotelDao) {

    suspend fun getHotelsPaging(fromId: Int, size: Int) = hotelDao.getHotelsPaging(fromId, size)

}