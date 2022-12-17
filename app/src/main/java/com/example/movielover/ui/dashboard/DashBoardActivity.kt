package com.example.movielover.ui.dashboard

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.movielover.BR
import com.example.movielover.R
import com.example.movielover.base.BaseActivity
import com.example.movielover.databinding.ActivityDashboardBinding
import com.example.movielover.ui.dashboard.favourites.FavouriteFragment
import com.example.movielover.ui.dashboard.home.HomeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class DashBoardActivity :AppCompatActivity(){

    private lateinit var binding: ActivityDashboardBinding



    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        setUpBottomNavView()


    }




    private fun initViews() {


        binding.bottomNav.setOnItemSelectedListener {
            return@setOnItemSelectedListener true
        }

        binding.bottomNav.setOnItemSelectedListener { menuItem ->


            when (menuItem.itemId) {
                R.id.homeFragment -> {
                    Log.d("invoked","invoked success")
                   changeNavigation(0)
                    return@setOnItemSelectedListener true
                }
                R.id.favouriteFragment -> {
                   changeNavigation(1)

                    return@setOnItemSelectedListener true
                }

                else -> {
                    changeNavigation(1)
                    return@setOnItemSelectedListener true
                }
            }
        }

//        binding.bottomNav.setOnNavigationItemReselectedListener {
//            when (it.itemId) {
//                R.id.homeFragment -> {
//                   loadFragment(HomeFragment())
//                    return@setOnNavigationItemReselectedListener
//                }
//                R.id.favouriteFragment -> {
//                   loadFragment(FavouriteFragment())
//                    return@setOnNavigationItemReselectedListener
//                }
//
//            }
//        }
    }

    private fun setUpBottomNavView() {
        // Finding the Navigation Controller
        navController = findNavController(R.id.navHostFragment)
        // Setting Navigation Controller with the BottomNavigationView
        binding.bottomNav.setupWithNavController(navController)
    }

    private  fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }

    @SuppressLint("RestrictedApi")
    private fun changeNavigation(position: Int) {

//        if (navController.backStack.count() > 1) {
//            val count = navController.backStack.count()
//            for (i in 0 until count)
//                navController.navigateUp()
//        }
        //   navController.popBackStack(R.id.navigation_home, false)

        navController.navigate(
            when (position) {
                0 -> R.id.homeFragment
                else -> R.id.favouriteFragment

            }
        )
    }



    companion object {

        fun start(context: Context) {
            context.startActivity(
                Intent(context, DashBoardActivity::class.java)
            )

        }
    }




}