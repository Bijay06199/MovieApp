package com.example.movielover.di

import com.example.movielover.ui.dashboard.favourites.FavouriteViewModel
import com.example.movielover.ui.dashboard.home.HomeViewModel
import com.example.movielover.ui.dashboard.home.detailMovie.DetailMovieViewModel
import com.example.movielover.ui.dashboard.home.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel { HomeViewModel(get(),get(),get()) }
    viewModel { FavouriteViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}