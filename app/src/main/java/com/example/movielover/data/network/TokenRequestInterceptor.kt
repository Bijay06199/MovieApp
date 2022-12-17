package com.example.movielover.data.network

import com.example.movielover.data.prefs.SharedPrefUtil
import okhttp3.Interceptor
import okhttp3.Response

class TokenRequestInterceptor(private val prefManager: SharedPrefUtil) : Interceptor {



    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        return if (!prefManager.accessToken.isNullOrEmpty())
            chain.proceed(
                original.newBuilder()
                    .addHeader("Authorization", "Bearer " + prefManager.accessToken!!)
                    .method(original.method, original.body)
                    .build()
            )
        else
            chain.proceed(
                original.newBuilder()
                    .method(original.method, original.body)
                    .build()
            )
    }

}