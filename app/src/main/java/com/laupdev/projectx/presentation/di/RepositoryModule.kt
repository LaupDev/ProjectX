package com.laupdev.projectx.presentation.di

import com.laupdev.projectx.data.database.HotelDao
import com.laupdev.projectx.data.database.UserDao
import com.laupdev.projectx.domain.repository.IRepository
import com.laupdev.projectx.domain.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {

    fun provideRepository(userDao: UserDao, hotelDao: HotelDao): IRepository {
        return Repository(userDao, hotelDao)
    }

    single { provideRepository(get(), get()) }
}