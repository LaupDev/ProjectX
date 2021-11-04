package com.laupdev.projectx.domain.workers.database

import com.laupdev.projectx.data.database.ContactInfo
import com.laupdev.projectx.data.database.Hotel
import com.laupdev.projectx.data.database.Picture
import com.laupdev.projectx.data.database.User

interface IDatabaseWorker {
    suspend fun getUserByUsername(username: String): User?
    suspend fun getUserByEmail(email: String): User?
    suspend fun getHotelsPaging(fromId: Int, size: Int): List<Hotel>
    suspend fun getHotelById(hotelId: Int): Hotel
    suspend fun getPicturesByHotelId(hotelId: Int): List<Picture>
    suspend fun getContactInfoByHotelId(hotelId: Int): ContactInfo
}