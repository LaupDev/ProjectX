package com.laupdev.projectx.presentation

import android.app.Application
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.laupdev.projectx.BuildConfig
import com.laupdev.projectx.data.database.AppDatabase
import com.laupdev.projectx.di.databaseModule
import com.laupdev.projectx.di.repositoryModule
import com.laupdev.projectx.di.viewModelModule
import com.laupdev.projectx.di.workerModule
import com.laupdev.projectx.domain.repository.IRepository
import com.laupdev.projectx.domain.repository.Repository
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

@Suppress("unused")
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        deleteDatabase("project_x_database")
        startKoin {
            androidContext(this@MyApplication)
            androidLogger(Level.DEBUG)
            modules(listOf(
                viewModelModule,
                repositoryModule,
                workerModule,
                databaseModule
            ))
        }
        val database: AppDatabase by inject()

        if (!databaseList().any { it == "project_x_database" }) {
            database.populateDatabase(this)
        }
        plantTimberTree()
    }

    private fun plantTimberTree() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}