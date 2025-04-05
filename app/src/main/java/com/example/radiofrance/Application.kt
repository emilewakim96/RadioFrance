package com.example.radiofrance

import android.app.Application
import com.example.data.di.networkModule
import com.example.radiofrance.di.presentationModule
import com.example.data.di.repositoryModule
import com.example.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(
                networkModule,
                repositoryModule,
                domainModule,
                presentationModule
            )
        }
    }
}