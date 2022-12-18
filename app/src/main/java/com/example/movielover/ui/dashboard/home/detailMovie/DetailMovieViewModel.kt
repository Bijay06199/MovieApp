package com.example.movielover.ui.dashboard.home.detailMovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movielover.base.BaseViewModel
import com.example.movielover.data.room.databaseManager.UserDatabaseManager
import com.example.movielover.data.room.model.FavouriteModel
import kotlinx.coroutines.launch

class DetailMovieViewModel(private val databaseManager: UserDatabaseManager):BaseViewModel() {

    val favouriteMovieList= MutableLiveData<List<FavouriteModel>>()

    fun saveFavourite(favouriteModel: FavouriteModel){
        viewModelScope.launch {
            databaseManager.saveFavourite(favouriteModel)
        }
    }

    fun getFavouriteMovies(){
        viewModelScope.launch {
            databaseManager.getFavourite().let {
                favouriteMovieList.value = it
            }

        }
    }


}