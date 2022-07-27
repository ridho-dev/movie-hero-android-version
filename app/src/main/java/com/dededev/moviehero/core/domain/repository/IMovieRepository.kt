package com.dededev.moviehero.core.domain.repository

import androidx.lifecycle.LiveData
import com.dededev.moviehero.core.data.Resource
import com.dededev.moviehero.core.data.source.remote.response.ResultsItem

interface IMovieRepository {
    fun getPopularMovies(): LiveData<Resource<List<ResultsItem>>>
}