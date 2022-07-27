package com.dededev.moviehero.core.domain.usecase

import androidx.lifecycle.LiveData
import com.dededev.moviehero.core.data.Resource
import com.dededev.moviehero.core.data.source.remote.response.ResultsItem

interface MovieUseCase {
    fun getPopularMovies(): LiveData<Resource<List<ResultsItem>>>
}