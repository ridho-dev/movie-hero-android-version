package com.dededev.moviehero.core.data.source.local

import androidx.lifecycle.LiveData
import com.dededev.moviehero.core.data.source.local.entity.MovieEntity
import com.dededev.moviehero.core.data.source.local.entity.SearchedMovieEntity
import com.dededev.moviehero.core.data.source.local.room.MovieDao

class LocalDataSource(private val movieDao: MovieDao){
    companion object {
        private val instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(movieDao)
            }
    }

    fun getPopularMovies(): LiveData<List<MovieEntity>> = movieDao.getPopularMovies()

    fun searchMovie(query: String): LiveData<List<SearchedMovieEntity>> = movieDao.searchMovie(query)

    fun getFavoriteMovies(): LiveData<List<MovieEntity>> = movieDao.getFavoriteMovies()

    fun insertPopularMovies(movieList: List<MovieEntity>) = movieDao.insertPopularMovies(movieList)

    fun insertSearchedMovies(movieList: List<SearchedMovieEntity>) = movieDao.insertSearchedMovies(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}