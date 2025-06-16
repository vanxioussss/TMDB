package com.tmdb.movies.state

import com.tmdb.model.Movie

/**
 * Created by van.luong
 * on 16,June,2025
 *
 * Represents the UI state for the movie list screen.
 */
sealed interface MovieUiState {
    /**
     * Represents the success state of the movie list screen.
     *
     * @property movies The list of movies to be displayed.
     */
    data class Success(
        val movies: List<Movie>
    ) : MovieUiState

    /**
     * Represents the loading state of the movie list screen.
     */
    data object Loading : MovieUiState

    /**
     * Represents the error state of the movie list screen.
     *
     * @property message The error message to be displayed.
     */
    data class Error(val message: String) : MovieUiState
}