package com.rezabintami.common.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMoviesResponse(
    @field:SerializedName("results")
    val result: List<MoviesResponse>
)