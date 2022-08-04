package com.dededev.moviehero

import android.app.Application
import com.dededev.core.di.databaseModule
import com.dededev.core.di.networkModule
import com.dededev.core.di.repositoryModule
import com.dededev.moviehero.di.useCaseModule
import com.dededev.moviehero.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}