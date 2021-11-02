package com.laupdev.projectx.di

import android.app.Application
import androidx.room.Room
import com.laupdev.projectx.database.AppDatabase
import com.laupdev.projectx.database.HotelDao
import com.laupdev.projectx.database.UserDao
import com.laupdev.projectx.model.HotelsRepository
import com.laupdev.projectx.model.HotelsViewModel
import com.laupdev.projectx.model.UserRepository
import com.laupdev.projectx.model.UserViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UserViewModel(get()) }
    viewModel { HotelsViewModel(get()) }
}

val repositoryModule = module {

    fun provideUserRepository(userDao: UserDao): UserRepository {
        return UserRepository(userDao)
    }

    fun provideHotelsRepository(hotelDao: HotelDao): HotelsRepository {
        return HotelsRepository(hotelDao)
    }

    single { provideUserRepository(get()) }
    single { provideHotelsRepository(get()) }
}

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

