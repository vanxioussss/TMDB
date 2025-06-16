package com.tmdb.model

/**
 * Created by van.luong
 * on 16,June,2025
 *
 * Data class representing a Movie.
 */
data class Movie(
    val id: Long,
    val backdropPath: String?,
    val posterPath: String?,
    val originalTitle: String,
    val releaseDate: String?,
    val voteAverage: Float?,
)

data class MoviePageResponse(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val results: List<Movie> = emptyList(),
)