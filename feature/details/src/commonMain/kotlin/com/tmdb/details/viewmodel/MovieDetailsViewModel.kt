package com.tmdb.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.common.resource.Resource
import com.tmdb.details.state.MovieDetailsUiState
import com.tmdb.domain.usecases.GetMovieDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Created by van.luong
 * on 17,June,2025
 */
class MovieDetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {
    private val _movieDetailUiState =
        MutableStateFlow<MovieDetailsUiState>(MovieDetailsUiState.Loading)
    val movieDetailUiState: StateFlow<MovieDetailsUiState> = _movieDetailUiState

    fun getMovieDetails(movieId: Long) = viewModelScope.launch {
        getMovieDetailsUseCase(movieId).collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    _movieDetailUiState.value = MovieDetailsUiState.ShowDetails(resource.body)
                }

                is Resource.DataError -> {
                    _movieDetailUiState.value = MovieDetailsUiState.Error(
                        resource.error.message ?: "Unknown error"
                    )
                }

                is Resource.ServerError -> {
                    _movieDetailUiState.value = MovieDetailsUiState.Error(
                        resource.error.message
                    )
                }

                is Resource.Loading -> {
                    _movieDetailUiState.value = MovieDetailsUiState.Loading
                }
            }
        }
    }
}