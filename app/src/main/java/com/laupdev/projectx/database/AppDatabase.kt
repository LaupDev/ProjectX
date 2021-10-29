package com.laupdev.projectx.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class, Hotel::class, Picture::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun hotelDao(): HotelDao
}