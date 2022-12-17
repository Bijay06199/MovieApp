package com.example.movielover.ui.dashboard.home.repository

import com.example.movielover.data.network.ApiService
import com.example.movielover.ui.dashboard.home.model.PopularResponse
import retrofit2.Response

interface PopularRepository {
    suspend fun getPopularMovies():Response<PopularResponse>
}

class PopularRepositoryImpl(
    private val apiService: ApiService
): PopularRepository {
    override suspend fun getPopularMovies(): Response<PopularResponse> {
        return apiService.getPopularMovies()

    }

}

