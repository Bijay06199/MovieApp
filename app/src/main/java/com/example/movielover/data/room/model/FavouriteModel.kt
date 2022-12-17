package com.example.movielover.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite")
data class FavouriteModel(


    val movieImage: String,
    val movieName: String,
    @PrimaryKey
    val movieId:Int,

    )