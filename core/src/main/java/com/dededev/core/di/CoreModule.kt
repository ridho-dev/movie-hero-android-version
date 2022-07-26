package com.dededev.core.di

import androidx.room.Room
import com.dededev.core.data.MovieRepository
import com.dededev.core.data.source.local.LocalDataSource
import com.dededev.core.data.source.local.room.MovieDatabase
import com.dededev.core.data.source.remote.RemoteDataSource
import com.dededev.core.data.source.remote.network.ApiService
import com.dededev.core.domain.repository.IMovieRepository
import com.dededev.core.utils.AppExecutors
import com.dededev.core.utils.Credentials
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dededev".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java,
            "Movie.db"
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> { MovieRepository(get(), get(), get()) }
}