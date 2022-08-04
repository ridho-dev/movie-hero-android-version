package com.dededev.moviehero.di

import com.dededev.moviehero.core.domain.usecase.MovieInteractor
import com.dededev.moviehero.core.domain.usecase.MovieUseCase
import com.dededev.moviehero.detail.DetailViewModel
import com.dededev.moviehero.favorite.FavoriteViewModel
import com.dededev.moviehero.home.HomeViewModel
import com.dededev.moviehero.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}