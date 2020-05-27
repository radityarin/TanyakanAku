package com.radityarin.tanyakanaku.di

import com.radityarin.tanyakanaku.data.repository.AppRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        AppRepository(get())
    }
}