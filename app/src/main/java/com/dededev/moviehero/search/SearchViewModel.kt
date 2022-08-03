package com.dededev.moviehero.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dededev.moviehero.core.domain.usecase.MovieUseCase

class SearchViewModel(private var movieUseCase: MovieUseCase): ViewModel() {
    fun searchMovies(query: String) = movieUseCase.searchMovie(query).asLiveData()
}