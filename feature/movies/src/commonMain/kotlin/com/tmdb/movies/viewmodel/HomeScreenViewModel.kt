package com.tmdb.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.common.resource.Resource
import com.tmdb.domain.usecases.GetTrendingMoviesUseCase
import com.tmdb.domain.usecases.SearchMoviesUseCase
import com.tmdb.model.Movie
import com.tmdb.movies.state.MovieUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
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

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val trendingMovies: StateFlow<Resource<List<Movie>>> = trendingMoviesUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, Resource.Loading)

    /**
     * Represents the UI state for the movie list screen.
     *
     * This state is updated based on the search query or trending movies.
     */
    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val movieUiState: StateFlow<MovieUiState> = _searchQuery
        .debounce(500)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            if (query.isNotBlank()) {
                flowOf(MovieUiState.ShowSearch(searchMoviesUseCase(query)))
            } else {
                trendingMovies.map { resource ->
                    when (resource) {
                        is Resource.Success -> MovieUiState.ShowTrending(resource.body)
                        is Resource.DataError -> MovieUiState.Error(
                            resource.error.message ?: "Unknown error"
                        )

                        is Resource.ServerError -> MovieUiState.Error(resource.error.message)
                        is Resource.Loading -> MovieUiState.Loading
                    }
                }
            }
        }
        .catch { MovieUiState.Error(it.message ?: "Unknown error") }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            MovieUiState.Loading
        )

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}