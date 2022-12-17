package com.example.movielover.ui.dashboard.home.search.repository

import com.example.movielover.data.network.ApiService
import com.example.movielover.ui.dashboard.home.search.model.SearchResponse
import com.example.movielover.ui.dashboard.home.model.NowPlayingResponse
import com.example.movielover.ui.dashboard.home.repository.NowPlayingRepository
import retrofit2.Response

interface SearchRepository {
    suspend fun getSearchMovies(name:String): Response<SearchResponse>
}

class SearchRepositoryImpl(
    private val apiService: ApiService
): SearchRepository {
    override suspend fun getSearchMovies(name: String): Response<SearchResponse> {
        return apiService.getSearchMovies(name)

    }

}