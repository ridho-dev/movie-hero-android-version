package com.dededev.moviehero.core.domain.usecase

import com.dededev.moviehero.core.data.Resource
import com.dededev.moviehero.core.domain.model.Movie
import com.dededev.moviehero.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getPopularMovies(): Flow<Resource<List<Movie>>> =
        movieRepository.getPopularMovies()

    override fun searchMovie(query: String): Flow<Resource<List<Movie>>> =
        movieRepository.searchMovie(query)

    override fun getFavoriteMovies(): Flow<List<Movie>> =
        movieRepository.getFavoriteMovies()

    override fun setFavoriteMovie(movie: Movie, newState: Boolean) =
        movieRepository.setFavoriteMovie(movie, newState)
}