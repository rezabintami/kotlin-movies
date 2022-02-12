package com.rezabintami.common.domain.usecase

import com.rezabintami.common.data.Resource
import com.rezabintami.common.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {
    fun getAllNowPlaying(): Flow<Resource<List<Movies>>>
    fun getAllPopular(): Flow<Resource<List<Movies>>>
    fun getAllTopRated(): Flow<Resource<List<Movies>>>
    fun getMovieDetail(id: String): Flow<Resource<Movies>>
    fun getFavoriteMovies(): Flow<List<Movies>>
    fun setFavoriteMovies(movies: Movies)
}