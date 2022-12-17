package com.example.movielover.ui.dashboard.home.repository

import com.example.movielover.data.network.ApiService
import com.example.movielover.ui.dashboard.home.model.NowPlayingResponse
import com.example.movielover.ui.dashboard.home.model.TrendingResponse
import retrofit2.Response

interface NowPlayingRepository {
    suspend fun getNowMovies(): Response<NowPlayingResponse>
}

class NowPlayingRepositoryImpl(
    private val apiService: ApiService
): NowPlayingRepository {
    override suspend fun getNowMovies(): Response<NowPlayingResponse> {
        return apiService.getNowMovies()

    }

}
