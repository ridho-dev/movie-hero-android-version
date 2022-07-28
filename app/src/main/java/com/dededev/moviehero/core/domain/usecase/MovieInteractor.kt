package com.dededev.moviehero.core.domain.usecase

import androidx.lifecycle.LiveData
import com.dededev.moviehero.core.data.Resource
import com.dededev.moviehero.core.domain.model.Movie
import com.dededev.moviehero.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getPopularMovies(): LiveData<Resource<List<Movie>>> =
        movieRepository.getPopularMovies()

    override fun getFavoriteMovies(): LiveData<List<Movie>> =
        movieRepository.getFavoriteMovies()

    override fun setFavoriteMovie(movie: Movie, newState: Boolean) =
        movieRepository.setFavoriteMovie(movie, newState)
}