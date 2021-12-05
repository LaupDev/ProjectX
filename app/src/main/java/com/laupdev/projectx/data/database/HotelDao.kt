package com.laupdev.projectx.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HotelDao {

    @Query("SELECT * FROM hotels WHERE id > :fromId LIMIT :size")
    suspend fun getHotelsPaging(fromId: Long, size: Long): List<Hotel>

    @Query("SELECT * FROM hotels WHERE id = :id")
    suspend fun getHotelById(id: Long): Hotel

    @Query("SELECT * FROM gallery WHERE hotel_id = :hotelId")
    suspend fun getPicturesByHotelId(hotelId: Long): List<Picture>

    @Query("SELECT * FROM hotels_contact_info WHERE hotel_id = :hotelId")
    suspend fun getContactsByHotelId(hotelId: Long): ContactInfo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotel(hotel: Hotel): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPicture(picture: Picture)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContactInfo(contactInfo: ContactInfo)
}