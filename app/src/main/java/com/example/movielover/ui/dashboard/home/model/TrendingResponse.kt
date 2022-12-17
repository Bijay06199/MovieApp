package com.example.movielover.ui.dashboard.home.model

data class TrendingResponse(
    val page: Int,
    val results: List<ResultX>,
    val total_pages: Int,
    val total_results: Int
)