package com.example.movielover.ui.dashboard.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movielover.base.BaseViewModel
import com.example.movielover.ui.dashboard.home.model.NowPlayingResponse
import com.example.movielover.ui.dashboard.home.model.PopularResponse
import com.example.movielover.ui.dashboard.home.model.Result
import com.example.movielover.ui.dashboard.home.model.TrendingResponse
import com.example.movielover.ui.dashboard.home.repository.NowPlayingRepository
import com.example.movielover.ui.dashboard.home.repository.PopularRepository
import com.example.movielover.ui.dashboard.home.repository.TrendingRepository
import com.example.movielover.utils.SingleLiveEvent
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel(val popularRepository: PopularRepository, val trendingRepository: TrendingRepository, val nowPlayingRepository: NowPlayingRepository) : BaseViewModel() {

    var popularResponse= MutableLiveData<PopularResponse>()
    var trendingResponse = MutableLiveData<TrendingResponse>()
    var nowPlayingResponse = MutableLiveData<NowPlayingResponse>()

    fun getPopularMovies() {
        viewModelScope.launch {
            try {

                 popularRepository.getPopularMovies().let {
                     Log.d("invokeddd",Gson().toJson(it.body()))
                     if (it.isSuccessful){
                         popularResponse.value=it.body()
                     }

                 }
            } catch (e: Exception) {
            }


        }
    }


    fun getTrendingMovies() {
        viewModelScope.launch {
            try {

                trendingRepository.getTrendingMovies().let {
                    Log.d("invokeddd",Gson().toJson(it.body()))
                    if (it.isSuccessful){
                        trendingResponse.value=it.body()
                    }

                }
            } catch (e: Exception) {
                showAlertDialog(e.toString())
            }


        }
    }

    fun getNowPlayingMovies() {
        viewModelScope.launch {
            try {

                nowPlayingRepository.getNowMovies().let {
                    Log.d("invokeddd",Gson().toJson(it.body()))
                    if (it.isSuccessful){
                        nowPlayingResponse.value=it.body()
                    }

                }
            } catch (e: Exception) {
            }


        }
    }
}