package com.laupdev.projectx.domain.repository

import com.laupdev.projectx.data.database.ContactInfo
import com.laupdev.projectx.data.database.Hotel
import com.laupdev.projectx.data.database.Picture
import com.laupdev.projectx.data.database.User

interface IRepository {
    suspend fun getLoggedInUser(): User?
    suspend fun getUserByEmail(email: String): User?
    suspend fun getHotelsPaging(fromId: Long, limit: Long): List<Hotel>
    suspend fun getHotelById(hotelId: Long): Hotel
    suspend fun getPicturesByHotelId(hotelId: Long): List<Picture>
    suspend fun getContactInfoByHotelId(hotelId: Long): ContactInfo
    suspend fun handleUserAuthData(email: String, password: String)
    suspend fun updateUserIsLoggedIn(userId: Int, isLoggedIn: Int)
    suspend fun signOut()

    fun populateFirestore()
}