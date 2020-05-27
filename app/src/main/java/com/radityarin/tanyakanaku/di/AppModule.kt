package com.radityarin.tanyakanaku.di

import com.radityarin.tanyakanaku.R
import org.koin.dsl.module
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

const val DEFAULT_FONT = "fonts/GoogleSans-Regular.ttf"

val appModule = module {

    single {
        CalligraphyConfig.Builder()
            .setDefaultFontPath(DEFAULT_FONT)
            .setFontAttrId(R.attr.fontPath)
            .build()
    }

}