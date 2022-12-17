package com.example.movielover.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.movielover.di.*
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApp : Application() {

    companion object {
        lateinit var instance: MovieApp
    }

    override fun attachBaseContext(base: Context?) {
        MultiDex.install(this)
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        instance = this

        startKoin {
            androidContext(this@MovieApp)
            modules(
                listOf(
                    viewModelModule,
                    appModule,
                    networkModule,
                    repositoryModule,
                    databaseModule
                )
            )
        }
    }

}