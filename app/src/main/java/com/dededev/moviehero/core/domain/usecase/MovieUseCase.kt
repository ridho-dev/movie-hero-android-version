package com.dededev.moviehero.core.domain.usecase

import com.dededev.moviehero.core.data.Resource
import com.dededev.moviehero.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getPopularMovies(): Flow<Resource<List<Movie>>>

    fun searchMovie(query: String): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, newState: Boolean)
}