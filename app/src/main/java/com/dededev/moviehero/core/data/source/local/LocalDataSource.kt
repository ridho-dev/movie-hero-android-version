package com.dededev.moviehero.core.data.source.local

import com.dededev.moviehero.core.data.source.local.entity.MovieEntity
import com.dededev.moviehero.core.data.source.local.entity.SearchedMovieEntity
import com.dededev.moviehero.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao){
    companion object {
        private val instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(movieDao)
            }
    }

    fun getPopularMovies(): Flow<List<MovieEntity>> = movieDao.getPopularMovies()

    fun searchMovie(query: String): Flow<List<SearchedMovieEntity>> = movieDao.searchMovie(query)

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    suspend fun insertPopularMovies(movieList: List<MovieEntity>) = movieDao.insertPopularMovies(movieList)

    suspend fun insertSearchedMovies(movieList: List<SearchedMovieEntity>) = movieDao.insertSearchedMovies(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}