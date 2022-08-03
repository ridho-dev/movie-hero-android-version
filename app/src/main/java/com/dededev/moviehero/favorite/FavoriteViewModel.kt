package com.dededev.moviehero.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dededev.moviehero.core.domain.model.Movie
import com.dededev.moviehero.core.domain.usecase.MovieUseCase

class FavoriteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getFavoriteMovies(): LiveData<List<Movie>> =
        movieUseCase.getFavoriteMovies().asLiveData()
}