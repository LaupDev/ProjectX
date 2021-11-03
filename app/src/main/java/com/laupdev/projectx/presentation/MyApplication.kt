package com.laupdev.projectx.presentation

import android.app.Application
import com.laupdev.projectx.BuildConfig
import com.laupdev.projectx.presentation.di.databaseModule
import com.laupdev.projectx.presentation.di.repositoryModule
import com.laupdev.projectx.presentation.di.viewModelModule
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