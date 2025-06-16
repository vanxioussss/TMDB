package com.tmdb.domain.usecases

import com.tmdb.domain.repository.MovieRepository

/**
 * Created by van.luong
 * on 16,June,2025
 *
 * Use case for getting trending movies.
 */
class GetTrendingMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    operator fun invoke() = movieRepository.getTrendingMovie()
}