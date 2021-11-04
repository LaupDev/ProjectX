package com.laupdev.projectx.di

import com.laupdev.projectx.data.database.HotelDao
import com.laupdev.projectx.data.database.UserDao
import com.laupdev.projectx.domain.workers.database.DatabaseWorker
import com.laupdev.projectx.domain.workers.database.IDatabaseWorker
import org.koin.dsl.module

val workerModule = module {
    fun provideDatabaseWorker(userDao: UserDao, hotelDao: HotelDao): IDatabaseWorker {
        return DatabaseWorker(userDao, hotelDao)
    }

    single { provideDatabaseWorker(get(), get()) }
}