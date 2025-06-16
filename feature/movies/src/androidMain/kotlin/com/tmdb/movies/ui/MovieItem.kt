package com.tmdb.movies.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.tmdb.model.Movie
import com.tmdb.ui.AppColorScheme

/**
 * Created by van.luong
 * on 16,June,2025
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieItem(
    movie: Movie,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(AppColorScheme.Background)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val posterUrl = movie.posterPath ?: movie.backdropPath

        GlideImage(
            model = posterUrl,
            contentDescription = movie.originalTitle,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(width = 90.dp, height = 130.dp)
                .clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = movie.originalTitle,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.White
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = movie.releaseDate ?: "Unknown Release Date",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFFFF3366), // pinkish like your screenshot
                    fontWeight = FontWeight.Medium
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "⭐ ${movie.voteAverage}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.7f)
            )
        }
    }
}

@Preview
@Composable
fun MovieItemPreview() {
    MovieItem(
        movie = Movie(
            id = 1,
            originalTitle = "Sample Movie",
            backdropPath = "/sample_backdrop.jpg",
            posterPath = "/sample_poster.jpg",
            releaseDate = "2025-06-16",
            voteAverage = 8.5f
        )
    )
}