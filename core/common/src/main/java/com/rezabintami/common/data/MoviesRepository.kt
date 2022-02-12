package com.rezabintami.common.data

import com.rezabintami.common.data.source.local.LocalDataSource
import com.rezabintami.common.data.source.remote.RemoteDataSource
import com.rezabintami.common.data.source.remote.network.ApiResponse
import com.rezabintami.common.data.source.remote.response.DetailMoviesResponse
import com.rezabintami.common.data.source.remote.response.MoviesResponse
import com.rezabintami.common.domain.model.Movies
import com.rezabintami.common.domain.repository.IMoviesRepository
import com.rezabintami.common.utils.AppExecutors
import com.rezabintami.common.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMoviesRepository {

    override fun getAllNowPlaying(): Flow<Resource<List<Movies>>> =
        object : NetworkBoundResource<List<Movies>, List<MoviesResponse>>() {

            override suspend fun createCall(): Flow<ApiResponse<List<MoviesResponse>>> =
                remoteDataSource.getAllMoviesNowPlaying()

            override suspend fun saveCallResult(data: List<MoviesResponse>): List<Movies> {
                val entities = DataMapper.mapResponsesListToEntitiesList(data)
                return DataMapper.mapEntitiesListToDomainList(entities)
            }
        }.asFlow()

    override fun getAllPopular(): Flow<Resource<List<Movies>>> =
        object : NetworkBoundResource<List<Movies>, List<MoviesResponse>>() {

            override suspend fun createCall(): Flow<ApiResponse<List<MoviesResponse>>> =
                remoteDataSource.getAllMoviesPopular()

            override suspend fun saveCallResult(data: List<MoviesResponse>): List<Movies> {
                val entities = DataMapper.mapResponsesListToEntitiesList(data)
                return DataMapper.mapEntitiesListToDomainList(entities)
            }
        }.asFlow()

    override fun getAllTopRated(): Flow<Resource<List<Movies>>> =
        object : NetworkBoundResource<List<Movies>, List<MoviesResponse>>() {

            override suspend fun createCall(): Flow<ApiResponse<List<MoviesResponse>>> =
                remoteDataSource.getAllMoviesTopRated()

            override suspend fun saveCallResult(data: List<MoviesResponse>): List<Movies> {
                val entities = DataMapper.mapResponsesListToEntitiesList(data)
                return DataMapper.mapEntitiesListToDomainList(entities)
            }
        }.asFlow()

    override fun getMovieDetail(id: String): Flow<Resource<Movies>> =
        object : NetworkBoundResource<Movies, DetailMoviesResponse>() {

            override suspend fun createCall(): Flow<ApiResponse<DetailMoviesResponse>> {
                return remoteDataSource.getAllMoviesDetail(id)
            }

            override suspend fun saveCallResult(data: DetailMoviesResponse): Movies {
                val entities = DataMapper.mapResponsesToEntities(data)
                return DataMapper.mapEntitiesToDomain(entities)
            }
        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movies>> {
        return localDataSource.getAllMoviesFavorites().map {
           DataMapper.mapEntitiesListToDomainList(it)
        }
    }

    override fun setFavoriteMovies(movies: Movies) {
        val movieEntity = DataMapper.mapDomainToEntity(movies)
        appExecutors.diskIO().execute { localDataSource.insertMoviesToFavorites(movieEntity) }
    }
}

