package com.tmdb.database.di

import com.tmdb.database.dao.MovieDetailsDao
import com.tmdb.database.dao.TrendingMoviesDao
import com.tmdb.database.providers.databaseInstance
import org.koin.dsl.module

/**
 * Created by van.luong
 * on 16,June,2025
 *
 * Provides the database module for dependency injection.
 */
val databaseModule =
    module {
        single<TrendingMoviesDao> {
            databaseInstance().trendingMoviesDao()
        }

        single<MovieDetailsDao> {
            databaseInstance().movieDetailsDao()
        }
    }