package com.dededev.moviehero.core.domain.usecase

import androidx.lifecycle.LiveData
import com.dededev.moviehero.core.data.Resource
import com.dededev.moviehero.core.domain.model.Movie

interface MovieUseCase {
    fun getPopularMovies(): LiveData<Resource<List<Movie>>>

    fun searchMovie(query: String): LiveData<Resource<List<Movie>>>

    fun getFavoriteMovies(): LiveData<List<Movie>>

    fun setFavoriteMovie(movie: Movie, newState: Boolean)
}