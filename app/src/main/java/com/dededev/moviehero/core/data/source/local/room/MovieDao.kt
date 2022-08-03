package com.dededev.moviehero.core.data.source.local.room

import androidx.room.*
import com.dededev.moviehero.core.data.source.local.entity.MovieEntity
import com.dededev.moviehero.core.data.source.local.entity.SearchedMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * from movie")
    fun getPopularMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM searched_movie where title LIKE '%' || :query || '%'")
    fun searchMovie(query: String?): Flow<List<SearchedMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchedMovies(movie: List<SearchedMovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieEntity)
}