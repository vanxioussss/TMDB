package com.tmdb.bridge.di

import com.tmbd.network.di.networkModule
import com.tmdb.data.datasource.MoviePagingSource
import com.tmdb.data.repository.MovieDetailsRepositoryImpl
import com.tmdb.data.repository.MovieRepositoryImpl
import com.tmdb.data.util.network.AndroidNetworkStatusTracker
import com.tmdb.data.util.network.NetworkTracker
import com.tmdb.database.di.databaseModule
import com.tmdb.domain.repository.MovieDetailsRepository
import com.tmdb.domain.repository.MovieRepository
import com.tmdb.domain.usecases.GetMovieDetailsUseCase
import com.tmdb.domain.usecases.GetTrendingMoviesUseCase
import com.tmdb.domain.usecases.SearchMoviesUseCase
import org.koin.dsl.module

/**
 * Created by van.luong
 * on 17,June,2025
 */

val dataModule =
    module {
        includes(networkModule)
        includes(databaseModule)

        single<MovieRepository> {
            MovieRepositoryImpl(
                movieDbApiService = get(),
                trendingMoviesDao = get(),
            )
        }

        single<MovieDetailsRepository> {
            MovieDetailsRepositoryImpl(
                movieDbApiService = get(),
                movieDetailsDao = get(),
            )
        }

        factory { (query: String) ->
            MoviePagingSource(
                apiService = get(),
                query = query,
            )
        }

        factory<NetworkTracker> {
            AndroidNetworkStatusTracker(get())
        }
    }

val domainModule = module {
    includes(dataModule)

    factory {
        SearchMoviesUseCase(
            movieRepository = get(),
        )
    }

    factory {
        GetTrendingMoviesUseCase(
            movieRepository = get(),
        )
    }

    factory {
        GetMovieDetailsUseCase(
            movieDetailsRepository = get(),
        )
    }
}