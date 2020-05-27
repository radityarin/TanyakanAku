package com.radityarin.tanyakanaku.di

import com.radityarin.tanyakanaku.ui.ask.AskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AskViewModel(get()) }
}