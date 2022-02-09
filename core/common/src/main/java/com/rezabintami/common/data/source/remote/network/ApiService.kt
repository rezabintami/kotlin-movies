package com.rezabintami.common.data.source.remote.network

import com.rezabintami.common.data.source.remote.response.ListMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie")
    suspend fun getMoviesNowPlaying(
        @Query("api_key") apiKey: String
    ): ListMoviesResponse
}
