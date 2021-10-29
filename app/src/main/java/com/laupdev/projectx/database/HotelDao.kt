package com.laupdev.projectx.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface HotelDao {

    @Query("SELECT * FROM hotels")
    suspend fun getAllHotels(): List<Hotel>

    @Query("SELECT * FROM hotels WHERE id = :id")
    fun getHotelById(id: Int): Hotel
}