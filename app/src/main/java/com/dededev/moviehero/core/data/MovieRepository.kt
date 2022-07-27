package com.dededev.moviehero.core.data

import androidx.lifecycle.LiveData
import com.dededev.moviehero.core.data.source.remote.RemoteDataSource
import com.dededev.moviehero.core.data.source.remote.response.ResultsItem
import com.dededev.moviehero.core.domain.repository.IMovieRepository
import com.dededev.moviehero.core.utils.AppExecutors

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository {
    companion object {
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteDataSource, appExecutors)
            }
    }

    override fun getPopularMovies(): LiveData<Resource<List<ResultsItem>>> {
        return remoteDataSource.getPopularMovies()
    }

//    override fun getPopularMovies(): LiveData<Resource<List<Movie>>> =
//        object : NetworkBoundResource<List<Movie>, List<ResultsItem>>(appExecutors) {
//            override fun loadFromDB(): LiveData<List<Movie>> {
//                return localDataSource.getPopularMovie()
//            }
//
//            override fun shouldFetch(data: List<Movie>?): Boolean = true
//
//            override fun createCall(): LiveData<ApiResponse<List<ResultsItem>>> {
//                return remoteDataSource.getPopularMovies()
//            }
//
//            override fun saveCallResult(data: List<ResultsItem>) {
//
//            }
//
//        }.asLiveData()
}