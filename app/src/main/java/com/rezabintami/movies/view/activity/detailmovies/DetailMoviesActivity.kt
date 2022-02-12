package com.rezabintami.movies.view.activity.detailmovies

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.rezabintami.common.data.Resource
import com.rezabintami.movies.R
import com.rezabintami.movies.databinding.ActivityMoviesDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMoviesActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMoviesDetailBinding
    private val viewModel: DetailMoviesViewModel by viewModels()
    private var movieId : String = ""
    companion object {
        const val MOVIE_ID = "movieID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        intentBundle()

        initViewModelObserver()
    }

    private fun intentBundle() {
         movieId = intent.getStringExtra(MOVIE_ID).toString()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movies_detail)
        binding.lifecycleOwner = this
    }

    @SuppressLint("SetTextI18n")
    private fun initViewModelObserver() {
        viewModel.detailMovies(movieId).observe(this, { movieDetail ->
            Log.d(TAG, "initViewModelObserver: $movieDetail")
            if(movieDetail != null) {
                when(movieDetail) {
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.nestedScrollView.visibility = View.GONE
                        binding.viewError.tvError.visibility = View.GONE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.tvError.visibility = View.GONE
                        binding.nestedScrollView.visibility = View.VISIBLE

                        movieDetail.data?.let { movie ->
                            Glide.with(this)
                                .load(getString(R.string.baseImageUrl, movie.posterPath))
                                .into(binding.imPoster)

                            binding.tvOverview.text = movie.overview
                            binding.tvPopularityValue.text = movie.popularity.toString()
                            binding.tvTitle.text = movie.title
                        }

                    }
                    is Resource.Error -> {
                        binding.nestedScrollView.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.tvError.visibility = View.VISIBLE
                        binding.viewError.tvError.text = getString(R.string.something_wrong)
                    }
                }
            }
        })
//        viewModel.movieDetail.observe(this, { movieDetail ->
//            if(movieDetail != null) {
//                when(movieDetail) {
//                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
//                    is Resource.Success -> {
//                        Log.d(TAG, "initViewModelObserver: ${movieDetail.data?.id}")
//                        movieDetail.data?.let { movie ->
////                            Glide.with(this)
////                                .load(getString(R.string.baseImageUrl, movie.posterPath))
////                                .into(binding.imPoster)
//
//                            binding.tvOverview.text = movie.overview
//                            binding.tvPopularityValue.text = movie.popularity.toString()
//                            binding.tvTitle.text = movie.title
//                        }
//
//                    }
//                    is Resource.Error -> binding.viewError.tvError.text = getString(R.string.something_wrong)
//                }
//            }
//        })
    }
}