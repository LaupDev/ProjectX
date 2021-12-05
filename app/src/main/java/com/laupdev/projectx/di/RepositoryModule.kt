package com.laupdev.projectx.di

import com.laupdev.projectx.domain.repository.IRepository
import com.laupdev.projectx.domain.repository.Repository
import com.laupdev.projectx.domain.workers.database.IDatabaseWorker
import com.laupdev.projectx.domain.workers.firestore.IHotelWorker
import com.laupdev.projectx.domain.workers.storage.IStorageWorker
import org.koin.dsl.module

val repositoryModule = module {

    fun provideRepository(databaseWorker: IDatabaseWorker, hotelWorker: IHotelWorker, storageWorker: IStorageWorker): IRepository {
        return Repository(databaseWorker, hotelWorker, storageWorker)
    }

    single { provideRepository(get(), get(), get()) }
}