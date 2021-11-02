package com.laupdev.projectx.database

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.laupdev.projectx.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

@Database(entities = [User::class, Hotel::class, Picture::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun hotelDao(): HotelDao

    fun populateDatabase(application: Application) {
        CoroutineScope(Dispatchers.IO).launch {
            userDao().insert(
                User(
                    username = "Admin",
                    email = "Admin@gmail.com",
                    password = "root"
                )
            )

            val hotelMainPhotoBitmap =
                BitmapFactory.decodeResource(application.resources, R.drawable.hotel_main_photo)
            application.applicationContext.openFileOutput("hotel_main_photo", Context.MODE_PRIVATE)
                .use {
                    hotelMainPhotoBitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                }
            for (i in 1..30) {

                hotelDao().insert(
                    Hotel(
                        name = "Hotel $i",
                        imagePath = File(application.filesDir, "hotel_main_photo").path
                    )
                )
            }
        }
    }
}