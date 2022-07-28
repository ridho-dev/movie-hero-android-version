package com.dededev.moviehero.core.di

import android.content.Context
import com.dededev.moviehero.core.data.MovieRepository
import com.dededev.moviehero.core.data.source.local.LocalDataSource
import com.dededev.moviehero.core.data.source.local.room.MovieDatabase
import com.dededev.moviehero.core.data.source.remote.RemoteDataSource
import com.dededev.moviehero.core.data.source.remote.network.ApiConfig
import com.dededev.moviehero.core.domain.repository.IMovieRepository
import com.dededev.moviehero.core.domain.usecase.MovieInteractor
import com.dededev.moviehero.core.domain.usecase.MovieUseCase
import com.dededev.moviehero.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IMovieRepository {
        val database = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideMovieUseCase(context: Context): MovieUseCase {
        val repository = provideRepository(context)
        return MovieInteractor(repository)
    }
}