package com.tmdb.details.state

import com.tmdb.model.MovieDetail

/**
 * Created by van.luong
 * on 17,June,2025
 *
 * Represents the UI state for the movie details screen.
 */
sealed interface MovieDetailsUiState {
    /**
     * Represents the state when movie details are displayed.
     *
     * @property data The movie detail data to be displayed.
     */
    data class ShowDetails(val data: MovieDetail) : MovieDetailsUiState

    /**
     * Represents the loading state of the movie details screen.
     */
    data object Loading : MovieDetailsUiState

    /**
     * Represents the error state of the movie details screen.
     *
     * @property message The error message to be displayed.
     */
    data class Error(val message: String) : MovieDetailsUiState
}