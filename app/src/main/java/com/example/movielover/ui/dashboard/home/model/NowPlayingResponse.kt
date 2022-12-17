package com.example.movielover.ui.dashboard.home.model

data class NowPlayingResponse(
    val dates: Dates,
    val page: Int,
    val results: List<ResultXX>,
    val total_pages: Int,
    val total_results: Int
)