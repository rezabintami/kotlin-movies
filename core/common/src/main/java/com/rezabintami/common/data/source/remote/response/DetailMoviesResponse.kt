package com.rezabintami.common.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailMoviesResponse (
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("vote_average")
    val popularity: Double,

    @field:SerializedName("poster_path")
    val posterPath: String,
)