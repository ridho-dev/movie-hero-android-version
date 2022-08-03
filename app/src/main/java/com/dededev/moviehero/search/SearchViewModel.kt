package com.dededev.moviehero.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dededev.moviehero.core.data.Resource
import com.dededev.moviehero.core.domain.model.Movie
import com.dededev.moviehero.core.domain.usecase.MovieUseCase

class SearchViewModel(private var movieUseCase: MovieUseCase): ViewModel() {
    fun searchMovies(query: String): LiveData<Resource<List<Movie>>> {
        return movieUseCase.searchMovie(query).asLiveData()
    }
}