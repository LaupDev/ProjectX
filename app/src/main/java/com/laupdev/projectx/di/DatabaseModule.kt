package com.laupdev.projectx.di

import android.app.Application
import androidx.room.Room
import com.laupdev.projectx.data.database.AppDatabase
import com.laupdev.projectx.data.database.HotelDao
import com.laupdev.projectx.data.database.UserDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): AppDatabase {
        val isAlreadyCreated = application.databaseList().any { it == "project_x_database" }
        val database = Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "project_x_database"
        )
            .build()
        if (!isAlreadyCreated) {
            database.populateDatabase(application)
        }
        return database
    }

    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    fun provideHotelDao(database: AppDatabase): HotelDao {
        return database.hotelDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideUserDao(get()) }
    single { provideHotelDao(get()) }
}