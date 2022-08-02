package com.dededev.moviehero.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dededev.moviehero.core.data.source.local.entity.MovieEntity
import com.dededev.moviehero.core.data.source.local.entity.SearchedMovieEntity

@Database(entities = [MovieEntity::class, SearchedMovieEntity::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "Movie.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}