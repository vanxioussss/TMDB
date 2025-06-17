package com.tmdb.movies.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.compose.itemKey
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
import app.cash.paging.compose.collectAsLazyPagingItems
import com.tmdb.movies.state.MovieUiState
import com.tmdb.movies.viewmodel.HomeScreenViewModel
import com.tmdb.navigation.navigateToDetails
import com.tmdb.ui.AppColorScheme
import com.tmdb.ui.ErrorBox
import com.tmdb.ui.searchTextFieldColors
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by van.luong
 * on 16,June,2025
 */
@Composable
fun MovieHomeScreenRoute(
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreenViewModel = koinViewModel(),
    navController: NavHostController
) {
    val uiState = homeScreenViewModel.movieUiState.collectAsStateWithLifecycle()
    val searchQuery = homeScreenViewModel.searchQuery.collectAsStateWithLifecycle()

    MovieHomeScreen(
        modifier = modifier,
        movieUiState = uiState.value,
        searchQuery = searchQuery.value,
        onQueryChange = { homeScreenViewModel.updateSearchQuery(it) },
        onMovieClick = { movieId ->
            navController.navigateToDetails(movieId)
        }
    )
}

@Composable
fun MovieHomeScreen(
    modifier: Modifier = Modifier,
    movieUiState: MovieUiState,
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onMovieClick: (Long) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppColorScheme.Background)
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { onQueryChange(it) },
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { onQueryChange("") }) {
                        Icon(
                            imageVector = Icons.Rounded.Close,
                            contentDescription = "Clear search"
                        )
                    }
                }
            },
            placeholder = { Text("Search movie") },
            singleLine = true,
            shape = RoundedCornerShape(24.dp),
            colors = searchTextFieldColors()
        )

        Text(
            text = if (searchQuery.isEmpty()) "Trending Movies" else "Search Results",
            style = MaterialTheme.typography.titleMedium,
            color = AppColorScheme.TextPrimary,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        when (movieUiState) {
            is MovieUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is MovieUiState.ShowTrending -> {
                LazyColumn(contentPadding = PaddingValues(16.dp)) {
                    items(movieUiState.data.size) { idx ->
                        val movie = movieUiState.data[idx]
                        MovieItem(movie = movie, onMovieClick = onMovieClick)
                    }
                }
            }

            is MovieUiState.ShowSearch -> {
                val pagingData = movieUiState.data.collectAsLazyPagingItems()
                val refreshState = pagingData.loadState.refresh
                val appendState = pagingData.loadState.append

                // Handle load states of the paging data
                when (refreshState) {
                    is LoadStateLoading -> {
                        // Initial full-screen loading
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is LoadStateError -> {
                        // Initial load error
                        ErrorBox(
                            modifier = modifier,
                            message = refreshState.error.localizedMessage ?: "Unknown error"
                        )
                    }

                    is LoadStateNotLoading -> {
                        LazyColumn(contentPadding = PaddingValues(16.dp)) {
                            items(
                                pagingData.itemCount,
                                key = pagingData.itemKey { it.id }) { idx ->
                                pagingData[idx]?.let { movie ->
                                    MovieItem(movie = movie, onMovieClick = onMovieClick)
                                }
                            }

                            // Append Loading → Pagination Spinner
                            if (appendState is LoadStateLoading) {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                }
                            }

                            // Append Error → Retry Button
                            if (appendState is LoadStateError) {
                                item {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                            Text(
                                                text = appendState.error.localizedMessage
                                                    ?: "Error loading more",
                                                color = AppColorScheme.PrimaryVariant
                                            )
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Button(onClick = { pagingData.retry() }) {
                                                Text("Retry")
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            is MovieUiState.Error -> {
                ErrorBox(
                    modifier = modifier,
                    message = movieUiState.message
                )
            }
        }
    }
}