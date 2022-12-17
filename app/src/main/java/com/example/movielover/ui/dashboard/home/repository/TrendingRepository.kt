package com.example.movielover.ui.dashboard.home.repository

import com.example.movielover.data.network.ApiService
import com.example.movielover.ui.dashboard.home.model.PopularResponse
import com.example.movielover.ui.dashboard.home.model.TrendingResponse
import retrofit2.Response

interface TrendingRepository {
    suspend fun getTrendingMovies(): Response<TrendingResponse>
}

class TrendingRepositoryImpl(
    private val apiService: ApiService
): TrendingRepository {
    override suspend fun getTrendingMovies(): Response<TrendingResponse> {
        return apiService.getTrendingMovies()

    }

}

