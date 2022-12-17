package com.example.movielover.data.room.databaseManager

import androidx.lifecycle.LiveData
import com.example.movielover.data.room.dao.FavouriteDao
import com.example.movielover.data.room.model.FavouriteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface UserDatabaseManager {
    suspend fun saveFavourite(favouriteModel: FavouriteModel)
    suspend fun getFavourite(): List<FavouriteModel>


}

class UserDatabaseManagerImpl(
    private val favouriteDao: FavouriteDao
):UserDatabaseManager{
    override suspend fun saveFavourite(favouriteModel: FavouriteModel) {
        withContext(Dispatchers.IO){
            favouriteDao.insert(favouriteModel)

        }
    }

    override suspend fun getFavourite(): List<FavouriteModel> {

        return withContext(Dispatchers.IO){
            favouriteDao.getFavouriteFromId()
        }

    }

}