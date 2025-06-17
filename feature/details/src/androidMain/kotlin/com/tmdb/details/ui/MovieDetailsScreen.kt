package com.tmdb.details.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AssistChip
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.tmdb.details.state.MovieDetailsUiState
import com.tmdb.details.viewmodel.MovieDetailsViewModel
import com.tmdb.model.MovieDetail
import com.tmdb.navigation.navigateToHome
import com.tmdb.ui.AppColorScheme
import org.koin.compose.viewmodel.koinViewModel

/**
 * Created by van.luong
 * on 17,June,2025
 */
@Composable
fun MovieDetailScreenRoute(
    modifier: Modifier = Modifier,
    movieId: Long,
    movieDetailsViewModel: MovieDetailsViewModel = koinViewModel(),
    navController: NavHostController
) {
    val uiState = movieDetailsViewModel.movieDetailUiState.collectAsStateWithLifecycle()

    LaunchedEffect(movieId) {
        movieDetailsViewModel.getMovieDetails(movieId)
    }

    MovieDetailScreen(
        modifier = modifier.background(AppColorScheme.Background),
        uiState = uiState.value,
        onBackClick = {
            navController.navigateToHome()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    uiState: MovieDetailsUiState,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Movie Detail", color = AppColorScheme.TextPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppColorScheme.Surface,
                    navigationIconContentColor = AppColorScheme.OnPrimary,
                    titleContentColor = AppColorScheme.OnPrimary,
                    actionIconContentColor = AppColorScheme.OnPrimary
                )
            )
        }
    ) { padding ->
        when (uiState) {
            is MovieDetailsUiState.Loading -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(AppColorScheme.Background),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is MovieDetailsUiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(AppColorScheme.Background),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Something went wrong: ${uiState.message}",
                        color = AppColorScheme.PrimaryVariant
                    )
                }
            }

            is MovieDetailsUiState.ShowDetails -> {
                val movie = uiState.data
                MovieDetailContent(movie, Modifier.padding(padding))
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieDetailContent(movieDetail: MovieDetail, modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(AppColorScheme.Background)
            .padding(16.dp)

    ) {
        GlideImage(
            model = movieDetail.posterPath,
            contentDescription = movieDetail.title,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(Modifier.height(16.dp))

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = movieDetail.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = movieDetail.tagline ?: "",
                style = MaterialTheme.typography.bodyMedium,
                fontStyle = FontStyle.Italic,
                color = AppColorScheme.TextPrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = movieDetail.releaseDate.takeIf { it.isNotEmpty() } ?: "Unknown Date",
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "⭐ ${movieDetail.voteAverage}",
                    style = MaterialTheme.typography.bodySmall
                )
                movieDetail.runtime?.let {
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "$it min",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = movieDetail.overview,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Genres
            if (movieDetail.genres.isNotEmpty()) {
                Text(
                    text = "Genres:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    movieDetail.genres.forEach {
                        AssistChip(
                            onClick = {},
                            label = { Text(it.name) }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Homepage
            movieDetail.homepage?.let { homepage ->
                if (homepage.isNotBlank()) {
                    Text(
                        text = "Visit Homepage",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.clickable {
                            uriHandler.openUri(homepage)
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // Collection
            movieDetail.belongsToCollection?.let { collection ->
                Text(
                    text = "Collection: ${collection.name}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Production Companies
            if (movieDetail.productionCompanies.isNotEmpty()) {
                Text(
                    text = "Production Companies:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                movieDetail.productionCompanies.forEach {
                    Text(text = "• ${it.name}", style = MaterialTheme.typography.bodySmall)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Production Countries
            if (movieDetail.productionCountries.isNotEmpty()) {
                Text(
                    text = "Production Countries:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                movieDetail.productionCountries.forEach {
                    Text(text = "• ${it.name}", style = MaterialTheme.typography.bodySmall)
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Spoken Languages
            if (movieDetail.spokenLanguages.isNotEmpty()) {
                Text(
                    text = "Spoken Languages:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                movieDetail.spokenLanguages.forEach {
                    Text(
                        text = "• ${it.englishName} (${it.iso6391})",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // IMDb link
            movieDetail.imdbId?.let { imdbId ->
                val imdbUrl = "https://www.imdb.com/title/$imdbId"
                Text(
                    text = "IMDb Page",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.clickable {
                        uriHandler.openUri(imdbUrl)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDetailsScreen() {

    MovieDetailScreen(uiState = MovieDetailsUiState.Loading, onBackClick = {})
}