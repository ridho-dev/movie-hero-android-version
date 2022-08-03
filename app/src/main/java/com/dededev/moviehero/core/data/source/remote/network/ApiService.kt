package com.dededev.moviehero.core.data.source.remote.network

import com.dededev.moviehero.core.data.source.remote.response.MovieResponse
import com.dededev.moviehero.core.utils.Credentials
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String = Credentials.API_KEY
    ): MovieResponse

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("api_key") api_key: String = Credentials.API_KEY
    ): MovieResponse
}