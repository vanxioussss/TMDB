package com.tmdb.details.di

import com.tmdb.details.viewmodel.MovieDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.lazyModule

/**
 * Created by van.luong
 * on 17,June,2025
 */
val detailsScreenModule = lazyModule {
    viewModel {
        MovieDetailsViewModel(
            getMovieDetailsUseCase = get()
        )
    }
}