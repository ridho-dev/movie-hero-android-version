package com.dededev.moviehero.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dededev.moviehero.core.data.Resource
import com.dededev.moviehero.core.data.source.remote.network.ApiService
import com.dededev.moviehero.core.data.source.remote.response.MovieResponse
import com.dededev.moviehero.core.data.source.remote.response.ResultsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService){
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService):RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getPopularMovies(): LiveData<Resource<List<ResultsItem>>> {
        val resultData = MutableLiveData<Resource<List<ResultsItem>>>()
        val client = apiService.getPopularMovies()

        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val dataArray = response.body()?.results
                Log.d("TAG", "onResponse: $dataArray")
                resultData.value =
                    if (dataArray != null) Resource.Success(dataArray)
                    else Resource.Error("")
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                resultData.value = Resource.Error(t.message.toString())
                Log.d("TAG", "onResponse: ${t.message.toString()}")
            }
        })
        return resultData
    }
}