package com.example.cardviewpanelapp.core

import android.app.Application
import com.example.cardviewpanelapp.BuildConfig
import com.example.cardviewpanelapp.di.presentationModule
import com.example.cardviewpanelapp.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        Timber.d("MyApp_TAG: onCreate: ")

        startKoin {
            if (BuildConfig.DEBUG) androidLogger()
            androidContext(this@MyApp)
            modules(
                repositoryModule + presentationModule
            )
        }
    }
}