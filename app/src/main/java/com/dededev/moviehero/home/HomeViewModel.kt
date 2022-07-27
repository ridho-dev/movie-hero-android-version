package com.dededev.moviehero.home

import androidx.lifecycle.ViewModel
import com.dededev.moviehero.core.domain.usecase.MovieUseCase

class HomeViewModel(private var movieUseCase: MovieUseCase) : ViewModel() {
    val getPopularMovies = movieUseCase.getPopularMovies()
}