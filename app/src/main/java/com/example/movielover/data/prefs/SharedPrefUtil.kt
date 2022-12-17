package com.example.movielover.data.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.movielover.app.MovieApp

class SharedPrefUtil(context: Context) {
    companion object{
        val sharedPrefs:SharedPrefUtil by lazy {
            SharedPrefUtil(MovieApp.instance)
        }
        private const val PREFS_FILENAME = "app_prefs"
        private const val ACCESS_TOKEN = "access_token"


    }
    private val sharePrefs: SharedPreferences =
        context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var accessToken: String?
        get() = sharePrefs.getString(ACCESS_TOKEN, null)
        set(value) = sharePrefs.edit { putString(ACCESS_TOKEN, value) }


}