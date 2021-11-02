package com.laupdev.projectx.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HotelDao {

    @Query("SELECT * FROM hotels WHERE id > :fromId LIMIT :size")
    suspend fun getHotelsPaging(fromId: Int, size: Int): List<Hotel>

    @Query("SELECT * FROM hotels WHERE id = :id")
    suspend fun getHotelById(id: Int): Hotel

    @Insert
    suspend fun insert(hotel: Hotel)
}