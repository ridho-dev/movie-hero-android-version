package com.dededev.moviehero.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dededev.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val getPopularMovies = movieUseCase.getPopularMovies().asLiveData()
}