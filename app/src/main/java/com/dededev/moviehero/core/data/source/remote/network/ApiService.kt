package com.dededev.moviehero.core.data.source.remote.network

import com.dededev.moviehero.core.data.source.remote.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

object ApiKey {
    const val api_key = "your_api_key"
}
interface ApiService {
    @GET("popular")
    fun getPopularMovies(
        @Query("api_key") api_key: String = ApiKey.api_key
    ): Call<MovieResponse>
}