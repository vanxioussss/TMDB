package com.tmdb.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.common.resource.Resource
import com.tmdb.domain.usecases.GetTrendingMoviesUseCase
import com.tmdb.domain.usecases.SearchMoviesUseCase
import com.tmdb.movies.state.MovieUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 * Created by van.luong
 * on 16,June,2025
 */
class HomeScreenViewModel(
    trendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {

    val movieUiState = trendingMoviesUseCase()
        .map { resource ->
            when (resource) {
                is Resource.Success -> MovieUiState.Success(resource.body)
                is Resource.DataError -> MovieUiState.Error(
                    resource.error.message ?: "Unknown error"
                )

                is Resource.ServerError -> MovieUiState.Error(
                    resource.error.message
                )

                is Resource.Loading -> MovieUiState.Loading
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = MovieUiState.Loading
        )
}