package com.example.movielover.di

import com.example.movielover.data.network.ApiService
import com.example.movielover.data.room.dao.FavouriteDao
import com.example.movielover.data.room.databaseManager.UserDatabaseManager
import com.example.movielover.data.room.databaseManager.UserDatabaseManagerImpl
import com.example.movielover.ui.dashboard.home.repository.*
import com.example.movielover.ui.dashboard.home.search.repository.SearchRepository
import com.example.movielover.ui.dashboard.home.search.repository.SearchRepositoryImpl
import org.koin.dsl.module

val repositoryModule= module {
    single { provideUserDaoManager(get()) }
    single { providePopularRepository(get()) }
    single { provideTrendingRepository(get() )}
    single { provideNowRepository(get()) }
    single { provideSearchRepository(get()) }

}

fun provideUserDaoManager(
    favouriteDao: FavouriteDao
): UserDatabaseManager {

    return UserDatabaseManagerImpl(favouriteDao)
}

fun providePopularRepository(apiService: ApiService): PopularRepository {
    return PopularRepositoryImpl(apiService)
}

fun provideTrendingRepository(apiService: ApiService): TrendingRepository {
    return TrendingRepositoryImpl(apiService)
}

fun provideNowRepository(apiService: ApiService): NowPlayingRepository {
    return NowPlayingRepositoryImpl(apiService)
}

fun provideSearchRepository(apiService: ApiService): SearchRepository {
    return SearchRepositoryImpl(apiService)
}