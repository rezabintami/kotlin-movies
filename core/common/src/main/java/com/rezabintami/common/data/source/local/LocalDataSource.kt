package com.rezabintami.common.data.source.local

import com.rezabintami.common.data.source.local.entity.MoviesEntity
import com.rezabintami.common.data.source.local.room.MoviesDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val moviesDao: MoviesDao) {

    fun getAllMoviesFavorites(): Flow<List<MoviesEntity>> = moviesDao.getAllMoviesFavorites()

    fun insertMovieToFavorites(movie: MoviesEntity) = moviesDao.insertMoviesToFavorites(movie)

    fun isFavorite(id: String) = moviesDao.isFavorite(id)

    fun removeMovieFromFavorite(movie: MoviesEntity) = moviesDao.removeMovies(movie)
}