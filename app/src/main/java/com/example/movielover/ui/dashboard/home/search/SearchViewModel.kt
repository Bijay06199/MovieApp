package com.example.movielover.ui.dashboard.home.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movielover.base.BaseViewModel
import com.example.movielover.ui.dashboard.home.search.model.SearchResponse
import com.example.movielover.ui.dashboard.home.search.repository.SearchRepository
import kotlinx.coroutines.launch

class SearchViewModel(val searchRepository: SearchRepository):BaseViewModel() {

    val searchResponse = MutableLiveData<SearchResponse>()


    fun getSearchMovies(name:String){

        viewModelScope.launch {
            try {
                searchRepository.getSearchMovies(name).let {
                    if (it.isSuccessful){
                        searchResponse.value = it.body()
                    }
                }
            }catch (e:Exception){
                showAlertDialog(e.toString())
            }

        }
    }
}