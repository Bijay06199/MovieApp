package com.example.movielover.data.network

import android.content.Context
import android.content.Intent
import com.example.movielover.data.prefs.SharedPrefUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class TokenAuthenticator constructor(
    private val mContext: Context,
    private val prefManager: SharedPrefUtil
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

//        val accessToken = response.headers["accessToken"]
//        val refreshToken = response.headers["refreshToken"]
        prefManager.accessToken="eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYWMzMjBmYmI5ZmVhMmE2NzE2Mzg4MWY2Y2IzNDQxZiIsInN1YiI6IjYzOTlkMWY2NmU0NGJmMDA4OTZlZDBmOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.DNlE9eAeGNehHGviO-ZXeDWJ0eAATiE0_X4dPpNXiQs"

//        accessToken?.let {
//            prefManager.accessToken = it
//        }
//        refreshToken?.let {
//            prefManager.refreshToken = it
//        }

        if (response.code == 401 || response.code == 404 || response.code == 500 || response.code == 400) {
            val intent = Intent()
//            intent.action = LOG_OUT
            mContext.sendBroadcast(intent)
        }
        return response
    }
}