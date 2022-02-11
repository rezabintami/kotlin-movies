package com.rezabintami.movies.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.rezabintami.movies.R
import com.rezabintami.movies.databinding.ActivityMoviesDetailBinding

class DetailMoviesActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMoviesDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movies_detail)
        binding.lifecycleOwner = this
    }
}