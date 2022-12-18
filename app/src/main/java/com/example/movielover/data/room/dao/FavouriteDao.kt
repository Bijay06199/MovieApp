package com.example.movielover.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movielover.base.BaseDao
import com.example.movielover.data.room.model.FavouriteModel

@Dao
interface FavouriteDao:BaseDao<FavouriteModel> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFavouriteMovie(favouriteModel: FavouriteModel)


    @Query("SELECT * FROM favourite")
    suspend fun getFavouriteFromId(): List<FavouriteModel>

    @Query("delete from favourite where movieId=:id")
    suspend fun deleteFavourite(id:Int)

}