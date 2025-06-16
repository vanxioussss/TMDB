package com.tmdb.movies.di

import com.tmdb.movies.viewmodel.HomeScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.lazyModule

/**
 * Created by van.luong
 * on 16,June,2025
 */
val homeScreenModule = lazyModule {
    viewModel {
        HomeScreenViewModel(
            trendingMoviesUseCase = get(),
            searchMoviesUseCase = get()
        )
    }
}