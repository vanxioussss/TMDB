package com.tmdb.movies.state

import app.cash.paging.PagingData
import com.tmdb.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Created by van.luong
 * on 16,June,2025
 *
 * Represents the UI state for the movie list screen.
 */
sealed interface MovieUiState {

    /**
     * Represents the state when trending movies are displayed.
     *
     * @property data The list of trending movies to be displayed.
     */
    data class ShowTrending(val data: List<Movie>) : MovieUiState

    /**
     * Represents the state when search results are displayed.
     *
     * @property data The flow of paginated movie data to be displayed.
     */
    data class ShowSearch(val data: Flow<PagingData<Movie>>) : MovieUiState

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