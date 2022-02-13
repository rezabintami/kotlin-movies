package com.rezabintami.movies.view.activity.detailmovies

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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

                        movieDetail.data?.let {
                            viewModel.bind(it)
                        }

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

        viewModel.isFavorite(movieId).observe(this, { movieDetail ->
            initClickListener(movieDetail)
            if(movieDetail) {
                binding.favoriteButton.setImageDrawable(
                    ContextCompat.getDrawable(
                        this.applicationContext,
                        R.drawable.ic_favorite_white
                    )
                )
            } else {

                binding.favoriteButton.setImageDrawable(
                    ContextCompat.getDrawable(
                        this.applicationContext,
                        R.drawable.ic_not_favorite_white
                    )
                )
            }
        })
    }

    private fun initClickListener(isFavorite: Boolean){
        if (isFavorite){
            binding.favoriteButton.setOnClickListener {
                viewModel.movie?.let { movie ->
                    viewModel.removeFavorite(movie)
                }
            }
        } else {
            binding.favoriteButton.setOnClickListener {
                viewModel.movie?.let { movie ->
                    viewModel.setFavorite(movie)
                }
            }
        }

    }
}