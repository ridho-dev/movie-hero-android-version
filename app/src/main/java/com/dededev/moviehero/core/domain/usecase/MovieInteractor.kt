package com.dededev.moviehero.core.domain.usecase

import androidx.lifecycle.LiveData
import com.dededev.moviehero.core.data.Resource
import com.dededev.moviehero.core.data.source.remote.response.ResultsItem
import com.dededev.moviehero.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getPopularMovies(): LiveData<Resource<List<ResultsItem>>> {
        return movieRepository.getPopularMovies()
    }
}