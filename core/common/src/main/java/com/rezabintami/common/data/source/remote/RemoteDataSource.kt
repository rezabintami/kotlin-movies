package com.rezabintami.common.data.source.remote

import com.rezabintami.common.data.source.remote.network.ApiResponse
import com.rezabintami.common.data.source.remote.network.ApiService
import com.rezabintami.common.data.source.remote.response.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    private val apiKey = "2174d146bb9c0eab47529b2e77d6b526"
    fun getAllMoviesNowPlaying(): Flow<ApiResponse<List<MoviesResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getMoviesNowPlaying(apiKey)
                val dataArray = response.result
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.result))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}

