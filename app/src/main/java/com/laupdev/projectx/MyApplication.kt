package com.laupdev.projectx

import android.app.Application
import com.laupdev.projectx.database.AppDatabase
import com.laupdev.projectx.di.databaseModule
import com.laupdev.projectx.di.repositoryModule
import com.laupdev.projectx.di.viewModelModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            androidLogger(Level.DEBUG)
            modules(listOf(
                viewModelModule,
                repositoryModule,
                databaseModule
            ))
        }
        plantTimberTree()
//        deleteDatabase("project_x_database")
    }

    private fun plantTimberTree() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}