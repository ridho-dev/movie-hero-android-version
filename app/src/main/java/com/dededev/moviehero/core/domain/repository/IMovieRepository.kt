package com.dededev.moviehero.core.domain.repository

import androidx.lifecycle.LiveData
import com.dededev.moviehero.core.data.Resource
import com.dededev.moviehero.core.domain.model.Movie

interface IMovieRepository {
    fun getPopularMovies(): LiveData<Resource<List<Movie>>>
}