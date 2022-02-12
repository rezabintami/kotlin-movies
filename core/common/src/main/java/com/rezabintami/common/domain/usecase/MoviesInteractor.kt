package com.rezabintami.common.domain.usecase

import com.rezabintami.common.domain.model.Movies
import com.rezabintami.common.domain.repository.IMoviesRepository
import javax.inject.Inject

class MoviesInteractor @Inject constructor(private val moviesRepository: IMoviesRepository): MoviesUseCase {

    override fun getAllNowPlaying() = moviesRepository.getAllNowPlaying()
    override fun getAllPopular() = moviesRepository.getAllPopular()
    override fun getAllTopRated() = moviesRepository.getAllTopRated()
    override fun getMovieDetail(id: String) = moviesRepository.getMovieDetail(id)

    override fun getFavoriteMovies() = moviesRepository.getFavoriteMovies()

    override fun setFavoriteMovies(movies: Movies) = moviesRepository.setFavoriteMovies(movies)
}