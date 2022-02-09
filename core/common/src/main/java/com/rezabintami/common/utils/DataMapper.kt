package com.rezabintami.common.utils

import com.rezabintami.common.data.source.local.entity.MoviesEntity
import com.rezabintami.common.data.source.remote.response.MoviesResponse
import com.rezabintami.common.domain.model.Movies

object DataMapper {
    fun mapResponsesToEntities(input: List<MoviesResponse>): List<MoviesEntity> {
        val moviesList = ArrayList<MoviesEntity>()
        input.map {
            val movies = MoviesEntity(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                popularity = it.popularity,
                overview = it.overview
            )
            moviesList.add(movies)
        }
        return moviesList
    }

    fun mapEntitiesToDomain(input: List<MoviesEntity>): List<Movies> =
        input.map {
            Movies(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                overview = it.overview,
                popularity = it.popularity
            )
        }


    fun mapDomainToEntity(input: Movies) = MoviesEntity(
        id = input.id,
        title = input.title,
        posterPath = input.posterPath,
        overview = input.overview,
        popularity = input.popularity
    )
}