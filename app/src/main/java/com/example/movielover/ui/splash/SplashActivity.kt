package com.example.movielover.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.movielover.R
import com.example.movielover.data.prefs.SharedPrefUtil
import com.example.movielover.ui.dashboard.DashBoardActivity
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {

    private val prefManager: SharedPrefUtil by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var handler = Handler()

        val r: Runnable = object : Runnable {
            override fun run() {
                DashBoardActivity.start(this@SplashActivity)
//                if (prefManager.isLoggedIn)
//                    DashBoardActivity.start(this@SplashActivity)
//                else
//                    LoginActivity.start(this@SplashActivity)

                finish()

            }
        }

        handler.postDelayed(r, 2000)
    }
}