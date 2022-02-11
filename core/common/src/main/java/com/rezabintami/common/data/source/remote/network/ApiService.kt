package com.rezabintami.common.data.source.remote.network

import com.rezabintami.common.data.source.remote.response.ListMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val GET_MOVIES_NOW_PLAYING = "movie/now_playing"
        const val GET_MOVIES_POPULAR = "movie/popular"
        const val GET_MOVIES_TOP_RATED = "movie/top_rated"
    }
    @GET(GET_MOVIES_NOW_PLAYING)
    suspend fun getMoviesNowPlaying(
        @Query("api_key") apiKey: String
    ): ListMoviesResponse

    @GET(GET_MOVIES_POPULAR)
    suspend fun getMoviesPopular(
        @Query("api_key") apiKey: String
    ): ListMoviesResponse

    @GET(GET_MOVIES_TOP_RATED)
    suspend fun getMoviesTopRated(
        @Query("api_key") apiKey: String
    ): ListMoviesResponse
}
