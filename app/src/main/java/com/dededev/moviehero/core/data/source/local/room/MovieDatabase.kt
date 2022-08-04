package com.dededev.moviehero.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dededev.moviehero.core.data.source.local.entity.MovieEntity
import com.dededev.moviehero.core.data.source.local.entity.SearchedMovieEntity

@Database(entities = [MovieEntity::class, SearchedMovieEntity::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao

}