package com.example.sofie_android_development_test.app.config

import com.example.sofie_android_development_test.app.ui.fragment.form.FormViewModel
import com.example.sofie_android_development_test.resources.remote.api.SofieAPI
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModules = module {
    viewModel { FormViewModel(androidContext()) }
}

val remoteModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { createApi<SofieAPI>(get()) }
}