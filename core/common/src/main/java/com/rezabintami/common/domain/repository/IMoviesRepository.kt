package com.rezabintami.common.domain.repository

import com.rezabintami.common.domain.model.Movies
import com.rezabintami.common.data.Resource
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {

    fun getAllNowPlaying(): Flow<Resource<List<Movies>>>


    fun getFavoriteMovies(): Flow<List<Movies>>

    fun setFavoriteMovies(movies: Movies)

}