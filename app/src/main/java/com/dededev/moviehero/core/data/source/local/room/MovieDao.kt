package com.dededev.moviehero.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dededev.moviehero.core.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * from movie")
    fun getPopularMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getFavoriteMovies(): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovies(movie: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}