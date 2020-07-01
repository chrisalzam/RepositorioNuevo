package com.example.cardviewpanelapp.di

import com.example.cardviewpanelapp.presentation.util.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        MainViewModel(get())
    }
}