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

    @Query("SELECT * FROM gallery WHERE hotel_id = :hotelId")
    suspend fun getPicturesByHotelId(hotelId: Int): List<Picture>

    @Query("SELECT * FROM hotels_contact_info WHERE hotel_id = :hotelId")
    suspend fun getContactsByHotelId(hotelId: Int): ContactInfo

    @Insert
    suspend fun insert(hotel: Hotel): Long

    @Insert
    suspend fun insertPicture(picture: Picture)

    @Insert
    suspend fun insertContactInfo(contactInfo: ContactInfo)
}