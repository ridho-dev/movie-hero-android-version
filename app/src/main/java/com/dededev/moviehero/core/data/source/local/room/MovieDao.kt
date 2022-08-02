package com.dededev.moviehero.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dededev.moviehero.core.data.source.local.entity.MovieEntity
import com.dededev.moviehero.core.data.source.local.entity.SearchedMovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * from movie")
    fun getPopularMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getFavoriteMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM searched_movie where title LIKE '%' || :query || '%'")
    fun searchMovie(query: String): LiveData<List<SearchedMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovies(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchedMovies(movie: List<SearchedMovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}