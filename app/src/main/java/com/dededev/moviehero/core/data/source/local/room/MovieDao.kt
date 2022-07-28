package com.dededev.moviehero.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dededev.moviehero.core.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * from movie")
    fun getPopularMovies(): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovies(movie: List<MovieEntity>)
}