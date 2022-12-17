package com.example.movielover.data.network

import com.example.movielover.ui.dashboard.home.search.model.SearchResponse
import com.example.movielover.ui.dashboard.home.model.NowPlayingResponse
import com.example.movielover.ui.dashboard.home.model.PopularResponse
import com.example.movielover.ui.dashboard.home.model.TrendingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

@GET(ApiEndPoints.POPULAR)
suspend fun getPopularMovies():Response<PopularResponse>

    @GET(ApiEndPoints.TRENDING)
    suspend fun getTrendingMovies():Response<TrendingResponse>

    @GET(ApiEndPoints.NOWPLAYING)
    suspend fun getNowMovies():Response<NowPlayingResponse>

    @GET(ApiEndPoints.SEARCHMOVIE)
    suspend fun getSearchMovies(
        @Query("query") query:String
    ):Response<SearchResponse>





}