package com.example.movielover.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movielover.data.room.converter.Converter
import com.example.movielover.data.room.dao.FavouriteDao
import com.example.movielover.data.room.model.FavouriteModel


@Database(
    entities = [
        FavouriteModel::class,
    ],
    version = 1,
    exportSchema = true
)

@TypeConverters(
    Converter::class
)



abstract class FavouriteDatabase:RoomDatabase() {
    abstract fun getFavouriteDao(): FavouriteDao
}