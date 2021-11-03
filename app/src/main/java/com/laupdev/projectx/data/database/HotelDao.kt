package com.laupdev.projectx.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HotelDao {

    @Query("SELECT * FROM hotels WHERE id > :fromId LIMIT :size")
    suspend fun getHotelsPaging(fromId: Int, size: Int): List<Hotel>

    @Query("SELECT * FROM hotels WHERE id = :id")
    suspend fun getHotelWithAllInfoById(id: Int): Hotel

    @Insert
    suspend fun insert(hotel: Hotel)
}