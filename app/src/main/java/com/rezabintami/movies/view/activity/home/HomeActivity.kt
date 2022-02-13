package com.rezabintami.movies.view.activity.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.rezabintami.movies.R
import com.rezabintami.movies.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    companion object {
        private const val ACTIVE_MENU_HOME = 1
        private const val ACTIVE_MENU_FAVORITES = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initNavigationController()
        setSelectedBottomMenu(ACTIVE_MENU_HOME)
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
                setSelectedBottomMenu(ACTIVE_MENU_HOME)

            }

            R.id.view_home_menu_2 -> {
                instantiateFragment()
                setSelectedBottomMenu(ACTIVE_MENU_FAVORITES)
                navController.navigate(R.id.action_to_favorites_page_new)
            }
        }
    }


    fun setSelectedBottomMenu(selectedMenu: Int) {
        when(selectedMenu){
             ACTIVE_MENU_HOME -> {
                 binding.imageViewHomeMenu1.setImageResource(
                    R.drawable.ic_home_red
                 )
                 binding.textViewHomeMenu1.setTextColor(ContextCompat.getColor(this, R.color.red))
                 binding.imageViewHomeMenu2.setImageResource(
                     R.drawable.ic_favorite_white
                 )
                 binding.textViewHomeMenu2.setTextColor(ContextCompat.getColor(this, R.color.white))
             }
             ACTIVE_MENU_FAVORITES -> {
                 binding.imageViewHomeMenu1.setImageResource(
                     R.drawable.ic_home
                 )
                 binding.textViewHomeMenu1.setTextColor(ContextCompat.getColor(this, R.color.white))
                 binding.imageViewHomeMenu2.setImageResource(
                     R.drawable.ic_favorite_red
                 )
                 binding.textViewHomeMenu2.setTextColor(ContextCompat.getColor(this, R.color.red))
             }
        }
    }

    private fun instantiateFragment(): Fragment? {
        return try {
            Class.forName("com.rezabintami.favorites.fragment.FavoritesFragment").newInstance() as Fragment
        } catch (e: Exception) {
            Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
            null
        }
    }
}