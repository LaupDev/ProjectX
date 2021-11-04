package com.laupdev.projectx.di

import com.laupdev.projectx.data.database.HotelDao
import com.laupdev.projectx.data.database.UserDao
import com.laupdev.projectx.domain.repository.IRepository
import com.laupdev.projectx.domain.repository.Repository
import com.laupdev.projectx.domain.workers.database.DatabaseWorker
import com.laupdev.projectx.domain.workers.database.IDatabaseWorker
import org.koin.dsl.module

val repositoryModule = module {

    fun provideRepository(databaseWorker: IDatabaseWorker): IRepository {
        return Repository(databaseWorker)
    }

    single { provideRepository(get()) }
}