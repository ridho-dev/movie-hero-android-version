package com.dededev.moviehero.detail

import androidx.lifecycle.ViewModel
import com.dededev.core.domain.model.Movie
import com.dededev.core.domain.usecase.MovieUseCase

class DetailViewModel(private var movieUseCase: MovieUseCase): ViewModel() {
    fun setFavoriteMovie(movie: Movie, newState: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newState)
}