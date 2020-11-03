package com.example.sofie_android_development_test.app

import android.app.Application
import com.example.sofie_android_development_test.app.config.remoteModule
import com.example.sofie_android_development_test.app.config.uiModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    private val appModules by lazy {
        listOf(remoteModule, uiModules)
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModules)
        }
    }
}