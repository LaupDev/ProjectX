package com.laupdev.projectx.di

import com.laupdev.projectx.data.database.HotelDao
import com.laupdev.projectx.data.database.UserDao
import com.laupdev.projectx.domain.workers.database.DatabaseWorker
import com.laupdev.projectx.domain.workers.database.IDatabaseWorker
import com.laupdev.projectx.domain.workers.firestore.FirestoreWorker
import com.laupdev.projectx.domain.workers.firestore.IFirestoreWorker
import org.koin.dsl.module

val workerModule = module {
    fun provideDatabaseWorker(userDao: UserDao, hotelDao: HotelDao): IDatabaseWorker {
        return DatabaseWorker(userDao, hotelDao)
    }

    fun provideFirestoreWorker(): IFirestoreWorker {
        return FirestoreWorker()
    }

    single { provideDatabaseWorker(get(), get()) }
    single { provideFirestoreWorker() }
}