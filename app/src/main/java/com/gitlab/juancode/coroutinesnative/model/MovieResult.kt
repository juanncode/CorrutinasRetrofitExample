package com.gitlab.juancode.coroutinesnative.model

import com.google.gson.annotations.SerializedName

data class MovieResult(
    val page: Int,
    val results: List<Movie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)