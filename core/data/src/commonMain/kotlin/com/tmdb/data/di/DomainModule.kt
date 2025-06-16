package com.tmdb.data.di

import com.tmdb.domain.usecases.GetTrendingMoviesUseCase
import com.tmdb.domain.usecases.SearchMoviesUseCase
import org.koin.dsl.module

/**
 * Created by van.luong
 * on 16,June,2025
 */
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
}