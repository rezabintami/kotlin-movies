package com.rezabintami.common.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies(
    val id: String,
    val title: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
) : Parcelable