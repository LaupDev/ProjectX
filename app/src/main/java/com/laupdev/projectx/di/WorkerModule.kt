package com.laupdev.projectx.di

import android.app.Application
import com.laupdev.projectx.data.database.HotelDao
import com.laupdev.projectx.data.database.UserDao
import com.laupdev.projectx.domain.workers.database.DatabaseWorker
import com.laupdev.projectx.domain.workers.database.IDatabaseWorker
import com.laupdev.projectx.domain.workers.firestore.HotelWorker
import com.laupdev.projectx.domain.workers.firestore.IHotelWorker
import com.laupdev.projectx.domain.workers.storage.IStorageWorker
import com.laupdev.projectx.domain.workers.storage.StorageWorker
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val workerModule = module {
    fun provideDatabaseWorker(userDao: UserDao, hotelDao: HotelDao): IDatabaseWorker {
        return DatabaseWorker(userDao, hotelDao)
    }

    fun provideHotelWorker(databaseWorker: IDatabaseWorker, storageWorker: IStorageWorker): IHotelWorker {
        return HotelWorker(databaseWorker, storageWorker)
    }

    fun provideStorageWorker(application: Application): IStorageWorker {
        return StorageWorker(application)
    }

    single { provideDatabaseWorker(get(), get()) }
    single { provideHotelWorker(get(), get()) }
    single { provideStorageWorker(androidApplication()) }
}