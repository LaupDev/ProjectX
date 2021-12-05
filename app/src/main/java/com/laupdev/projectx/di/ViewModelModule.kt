package com.laupdev.projectx.di

import com.laupdev.projectx.presentation.ui.hotel.HotelDetailsViewModel
import com.laupdev.projectx.presentation.ui.hotels_list.HotelsListViewModel
import com.laupdev.projectx.presentation.ui.start.StartViewModel
import com.laupdev.projectx.presentation.ui.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UserViewModel(get()) }
    viewModel { HotelsListViewModel(get()) }
    viewModel { HotelDetailsViewModel(get()) }
    viewModel { StartViewModel(get()) }
}


