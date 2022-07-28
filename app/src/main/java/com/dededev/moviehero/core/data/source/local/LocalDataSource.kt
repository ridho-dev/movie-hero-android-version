package com.dededev.moviehero.core.data.source.local

import androidx.lifecycle.LiveData
import com.dededev.moviehero.core.data.source.local.entity.MovieEntity
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

    fun insertPopularMovies(movieList: List<MovieEntity>) = movieDao.insertPopularMovies(movieList)
}