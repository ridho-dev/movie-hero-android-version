package com.dededev.moviehero.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dededev.moviehero.core.data.source.local.LocalDataSource
import com.dededev.moviehero.core.data.source.remote.RemoteDataSource
import com.dededev.moviehero.core.data.source.remote.network.ApiResponse
import com.dededev.moviehero.core.data.source.remote.response.ResultsItem
import com.dededev.moviehero.core.domain.model.Movie
import com.dededev.moviehero.core.domain.repository.IMovieRepository
import com.dededev.moviehero.core.utils.AppExecutors
import com.dededev.moviehero.core.utils.DataMapper

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository {
    companion object {
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteDataSource, localDataSource, appExecutors)
            }
    }

    override fun getPopularMovies(): LiveData<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<ResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Movie>> {
                return Transformations.map(localDataSource.getPopularMovies()) {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = true

            override fun createCall(): LiveData<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getPopularMovies()

            override fun saveCallResult(data: List<ResultsItem>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertPopularMovies(movieList)
            }
        }.asLiveData()

    override fun searchMovie(query: String): LiveData<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<ResultsItem>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Movie>> {
                return Transformations.map(localDataSource.searchMovie(query)) {
                    DataMapper.mapSearchEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = true

            override fun createCall(): LiveData<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.searchMovies(query)

            override fun saveCallResult(data: List<ResultsItem>) {
                val movieList = DataMapper.mapResponsesToSearchedEntities(data)
                localDataSource.insertSearchedMovies(movieList)
            }

        }.asLiveData()

    override fun getFavoriteMovies(): LiveData<List<Movie>> {
        return Transformations.map(localDataSource.getFavoriteMovies()) {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, newState: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, newState) }
    }
}