package com.rezabintami.movies.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rezabintami.movies.R
import com.rezabintami.movies.databinding.ActivityMainBinding

class HomeActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onViewClicked(view: View) {
        when(view.id) {
            R.id.view_home_menu_1 -> {

            }

            R.id.view_home_menu_2 -> {

            }
        }
    }
}