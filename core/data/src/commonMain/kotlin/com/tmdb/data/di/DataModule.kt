package com.tmdb.data.di

import com.tmbd.network.di.networkModule
import com.tmdb.data.repository.MovieRepositoryImpl
import com.tmdb.database.di.databaseModule
import com.tmdb.domain.repository.MovieRepository
import org.koin.dsl.module

/**
 * Created by van.luong
 * on 16,June,2025
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
    }