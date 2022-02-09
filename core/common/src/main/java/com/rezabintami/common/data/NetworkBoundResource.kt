package com.rezabintami.common.data

import com.rezabintami.common.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emit(Resource.Success(saveCallResult(apiResponse.data)))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Error<ResultType>("Empty Data"))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error<ResultType>(apiResponse.errorMessage))
            }
        }
    }

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType): ResultType

    fun asFlow(): Flow<Resource<ResultType>> = result
}