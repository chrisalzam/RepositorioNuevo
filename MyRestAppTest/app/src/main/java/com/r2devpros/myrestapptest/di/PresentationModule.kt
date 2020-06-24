package com.r2devpros.myrestapptest.di

import com.r2devpros.myrestapptest.presentation.locator.LocatorViewModel
import com.r2devpros.myrestapptest.presentation.storeMap.StoreMapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { LocatorViewModel(get()) }
    viewModel { StoreMapViewModel() }
}