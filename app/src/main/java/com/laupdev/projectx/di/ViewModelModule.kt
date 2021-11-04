package com.laupdev.projectx.di

import com.laupdev.projectx.presentation.hotel.HotelsViewModel
import com.laupdev.projectx.presentation.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UserViewModel(get()) }
    viewModel { HotelsViewModel(get()) }
}


