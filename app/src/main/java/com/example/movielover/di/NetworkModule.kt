package com.example.movielover.di

import com.example.movielover.data.network.ApiService
import com.example.movielover.data.network.TokenAuthenticator
import com.example.movielover.data.network.TokenRequestInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule= module {
    single { createOkHttpClient(get(), get()) }
    single { TokenAuthenticator(get(), get()) }
    single { TokenRequestInterceptor(get()) }
    single { createWebService<ApiService>(get(), "https://api.themoviedb.org/3/") }
}

fun createOkHttpClient(
    tokenAuthenticator: TokenAuthenticator,
    tokenRequestInterceptor: TokenRequestInterceptor
): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val okHttpClientBuilder = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(2, TimeUnit.MINUTES)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(tokenAuthenticator)
        .addInterceptor(tokenRequestInterceptor)
        .addNetworkInterceptor(StethoInterceptor())
    return okHttpClientBuilder.build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    return retrofit.create(T::class.java)

}