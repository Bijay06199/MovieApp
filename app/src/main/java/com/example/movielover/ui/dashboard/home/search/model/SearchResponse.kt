package com.example.movielover.ui.dashboard.home.search.model

data class SearchResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)