package com.example.movielover.ui.dashboard.favourites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movielover.base.BaseViewModel
import com.example.movielover.data.room.databaseManager.UserDatabaseManager
import com.example.movielover.data.room.model.FavouriteModel
import kotlinx.coroutines.launch

class FavouriteViewModel(private val databaseManager: UserDatabaseManager,
):BaseViewModel() {

    val favouriteMovieList=MutableLiveData<List<FavouriteModel>>()

    fun getFavouriteMovies(){
        viewModelScope.launch {
            databaseManager.getFavourite().let {
                favouriteMovieList.value = it
            }

        }
    }


}