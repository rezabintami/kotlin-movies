package com.rezabintami.movies.view.activity.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.rezabintami.movies.R
import com.rezabintami.movies.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initNavigationController()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }

    private fun initNavigationController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_home_host) as NavHostFragment
        navController = navHostFragment.navController
    }

    fun onViewClicked(view: View) {
        when(view.id) {
            R.id.view_home_menu_1 -> {
                navController.navigate(R.id.action_to_home_page_new)
            }

            R.id.view_home_menu_2 -> {
                navController.navigate(R.id.action_to_favorites_page_new)
            }
        }
    }
}