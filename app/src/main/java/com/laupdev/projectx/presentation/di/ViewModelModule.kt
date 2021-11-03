package com.laupdev.projectx.presentation.di

import com.laupdev.projectx.domain.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UserViewModel(get()) }
    viewModel { HotelsViewModel(get()) }
}


