package com.example.movielover.di

import android.content.Context
import android.content.res.Resources
import com.example.movielover.app.MovieApp
import com.example.movielover.data.prefs.SharedPrefUtil
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.dsl.module

val appModule = module {
    single { provideSharedPreference() }

    single { provideResources(get()) }
    factory { CoroutineScope(Dispatchers.Main + Job()) }

    single { Gson() }
}


fun provideSharedPreference(): SharedPrefUtil {
    val sharedPref: SharedPrefUtil by lazy {
        SharedPrefUtil(MovieApp.instance)
    }
    return sharedPref
}

fun provideResources(context: Context): Resources = context.resources