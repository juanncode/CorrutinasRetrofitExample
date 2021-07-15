package com.gitlab.juancode.coroutinesnative.server

import com.gitlab.juancode.coroutinesnative.model.MovieResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("discover/movie")
    fun listPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String = "es"
    ): Call<MovieResult>
}