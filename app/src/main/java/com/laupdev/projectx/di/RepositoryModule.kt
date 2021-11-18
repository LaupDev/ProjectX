package com.laupdev.projectx.di

import com.laupdev.projectx.domain.repository.IRepository
import com.laupdev.projectx.domain.repository.Repository
import com.laupdev.projectx.domain.workers.database.IDatabaseWorker
import com.laupdev.projectx.domain.workers.firestore.IFirestoreWorker
import org.koin.dsl.module

val repositoryModule = module {

    fun provideRepository(databaseWorker: IDatabaseWorker, firestoreWorker: IFirestoreWorker): IRepository {
        return Repository(databaseWorker, firestoreWorker)
    }

    single { provideRepository(get(), get()) }
}