package com.tmdb.movies.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tmdb.model.Movie
import com.tmdb.movies.state.MovieUiState
import com.tmdb.movies.viewmodel.HomeScreenViewModel
import com.tmdb.ui.AppColorScheme
import com.tmdb.ui.searchTextFieldColors
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by van.luong
 * on 16,June,2025
 */
@Composable
fun MovieHomeScreenRoute(
    modifier: Modifier = Modifier,
    homeScreenViewModel: HomeScreenViewModel = koinViewModel()
) {
    val uiState = homeScreenViewModel.movieUiState.collectAsStateWithLifecycle()
    MovieHomeScreen(
        modifier = modifier,
        movieUiState = uiState.value
    )
}

@Composable
fun MovieHomeScreen(
    modifier: Modifier = Modifier,
    movieUiState: MovieUiState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppColorScheme.Background)
    ) {
        OutlinedTextField(
            value = "searchText",
            onValueChange = { /** TODO */ },
            modifier = modifier
                .fillMaxWidth()
                .padding(12.dp),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            placeholder = { Text("Search movie") },
            singleLine = true,
            shape = RoundedCornerShape(24.dp),
            colors = searchTextFieldColors()
        )

        Text(
            text = if (true) "Trending Movies" else "Search Results",
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

            is MovieUiState.Success -> {
                LazyColumn(contentPadding = PaddingValues(16.dp)) {
                    items(movieUiState.movies.size) { idx ->
                        val movie = movieUiState.movies[idx]
                        MovieItem(movie = movie)
                    }
                }
            }

            is MovieUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = movieUiState.message, color = AppColorScheme.PrimaryVariant)
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieHomeScreenPreview() {
    MovieHomeScreen(
        movieUiState = MovieUiState.Success(
            movies = listOf(
                Movie(
                    id = 1L,
                    originalTitle = "Inception",
                    posterPath = "https://image.tmdb.org/t/p/w500/inception.jpg",
                    backdropPath = "https://image.tmdb.org/t/p/w500/inception_backdrop.jpg",
                    releaseDate = "2010-07-16",
                    voteAverage = 8.8f
                ),
                Movie(
                    id = 2L,
                    originalTitle = "The Dark Knight",
                    posterPath = "https://image.tmdb.org/t/p/w500/dark_knight.jpg",
                    backdropPath = "https://image.tmdb.org/t/p/w500/dark_knight_backdrop.jpg",
                    releaseDate = "2008-07-18",
                    voteAverage = 9.0f
                )
            )
        )
    )
}