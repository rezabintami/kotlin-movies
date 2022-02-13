package com.rezabintami.movies.view.activity.detailmovies

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rezabintami.common.data.Resource
import com.rezabintami.common.domain.model.Movies
import com.rezabintami.common.domain.usecase.MoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailMoviesViewModel @Inject constructor(
        private val moviesUseCase: MoviesUseCase
    ): ViewModel() {
    var movie: Movies? = null

    fun detailMovies(id: String) = moviesUseCase.getMovieDetail(id).asLiveData()

    fun isFavorite(id: String) = moviesUseCase.isFavorite(id).asLiveData()

    fun setFavorite(movie: Movies) = moviesUseCase.setFavoriteMovies(movie)

    fun removeFavorite(movie: Movies) = moviesUseCase.removeFavoriteMovies(movie)

    fun bind(mov: Movies) {
        movie = Movies(
            id = mov.id,
            title = mov.title,
            overview = mov.overview,
            popularity = mov.popularity,
            posterPath = mov.posterPath
        )
    }
}