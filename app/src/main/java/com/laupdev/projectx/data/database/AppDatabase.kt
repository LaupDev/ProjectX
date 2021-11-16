package com.laupdev.projectx.data.database

import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.Database
import androidx.room.RoomDatabase
import com.laupdev.projectx.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

@Database(entities = [User::class, Hotel::class, Picture::class, ContactInfo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun hotelDao(): HotelDao


    fun populateDatabase(application: Application) {
        CoroutineScope(Dispatchers.IO).launch {
            loadPictureToFileSystem(
                "hotel_main_photo",
                R.drawable.hotel_main_photo,
                application
            )
            loadPictureToFileSystem("picture_1", R.drawable.picture_1, application)
            loadPictureToFileSystem("picture_2", R.drawable.picture_2, application)
            loadPictureToFileSystem("picture_3", R.drawable.picture_3, application)
            loadPictureToFileSystem("picture_4", R.drawable.picture_4, application)
            for (i in 1..30) {
                val hotelDao = hotelDao()
                val newHotelId = hotelDao.insert(
                    Hotel(
                        name = "Hotel $i",
                        imagePath = File(application.filesDir, "hotel_main_photo").path,
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
                    )
                )
                for (pictureInd in 1..4) {
                    hotelDao.insertPicture(
                        Picture(
                            hotel_id = newHotelId,
                            imageTitle = "Picture $pictureInd",
                            imagePath = File(application.filesDir, "picture_$pictureInd").path
                        )
                    )
                }
                hotelDao.insertContactInfo(
                    ContactInfo(
                        hotelId = newHotelId,
                        address = "262 East Railroad St.Painesville, OH 44077",
                        contacts = "Ofra Torunn Altansarnai: +421992349421|Regena Borghildr Olympias: +421992416352"
                    )
                )
            }
        }
    }

    private fun loadPictureToFileSystem(
        pictureName: String,
        resourceId: Int,
        application: Application
    ) {
        val picture = BitmapFactory.decodeResource(application.resources, resourceId)
        application.applicationContext.openFileOutput(pictureName, Context.MODE_PRIVATE)
            .use {
                picture.compress(Bitmap.CompressFormat.JPEG, 100, it)
            }
    }
}