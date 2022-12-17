package com.example.movielover.di

import android.app.Application
import androidx.room.Room
import com.example.movielover.data.room.FavouriteDatabase
import com.example.movielover.data.room.dao.FavouriteDao
import com.example.movielover.utils.constants.AppContracts
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideFavourite(get()) }

}


fun provideDatabase(
    context: Application
): FavouriteDatabase {
    return Room.databaseBuilder(
        context,
        FavouriteDatabase::class.java,
        AppContracts.Strings.DATABASE_NAME
    )
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()
}

fun provideFavourite(favouriteDatabase: FavouriteDatabase): FavouriteDao = favouriteDatabase.getFavouriteDao()




