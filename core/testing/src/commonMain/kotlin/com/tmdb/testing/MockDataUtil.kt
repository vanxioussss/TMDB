package com.tmdb.testing

import com.tmdb.model.Movie

/**
 * Created by van.luong
 * on 17,June,2025
 */
object MockDataUtil {
    fun mockMovieList() = listOf(
        Movie(
            id = 123L,
            originalTitle = "Mock Remote Movie",
            releaseDate = "2025-01-01",
            posterPath = "/mockPoster.jpg",
            backdropPath = "/mockBackdrop.jpg",
            voteAverage = 8.5f
        ),
        Movie(
            id = 456L,
            originalTitle = "Another Mock Movie",
            releaseDate = "2025-02-01",
            posterPath = "/mockPoster2.jpg",
            backdropPath = "/mockBackdrop2.jpg",
            voteAverage = 7.2f
        )
    )
}