package com.laupdev.projectx.domain.workers.firestore

import com.laupdev.projectx.data.database.ContactInfo
import com.laupdev.projectx.data.database.Hotel
import com.laupdev.projectx.data.database.Picture

interface IHotelWorker {

    suspend fun getHotelsUsingPaging(fromId: Long, limit: Long): List<Hotel>

    suspend fun getHotelById(hotelId: Long): Hotel

    suspend fun getContactInfoByHotelId(hotelId: Long): ContactInfo

    suspend fun getPicturesByHotelId(hotelId: Long): List<Picture>

    fun populateFirestore()
}