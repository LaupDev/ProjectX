package com.laupdev.projectx.domain.repository

import com.laupdev.projectx.data.database.ContactInfo
import com.laupdev.projectx.data.database.Hotel
import com.laupdev.projectx.domain.workers.database.IDatabaseWorker

class Repository(private val databaseWorker: IDatabaseWorker): IRepository {

    override suspend fun getUserByUsername(username: String) = databaseWorker.getUserByUsername(username)

    override suspend fun getUserByEmail(email: String) = databaseWorker.getUserByEmail(email)

    override suspend fun getHotelsPaging(fromId: Int, size: Int) = databaseWorker.getHotelsPaging(fromId, size)

    override suspend fun getHotelById(hotelId: Int) = databaseWorker.getHotelById(hotelId)

    override suspend fun getPicturesByHotelId(hotelId: Int) = databaseWorker.getPicturesByHotelId(hotelId)

    override suspend fun getContactInfoByHotelId(hotelId: Int) = databaseWorker.getContactInfoByHotelId(hotelId)
}