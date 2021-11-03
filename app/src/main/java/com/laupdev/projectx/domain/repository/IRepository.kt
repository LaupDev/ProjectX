package com.laupdev.projectx.domain.repository

import com.laupdev.projectx.data.database.Hotel
import com.laupdev.projectx.data.database.User

interface IRepository {

    suspend fun getUserByUsername(username: String): User?
    suspend fun getUserByEmail(email: String): User?
    suspend fun getHotelsPaging(fromId: Int, size: Int): List<Hotel>
    suspend fun getHotelWithAllInfo(hotelId: Int): Hotel

}