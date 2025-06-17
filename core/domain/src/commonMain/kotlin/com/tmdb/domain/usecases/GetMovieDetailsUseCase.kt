package com.tmdb.domain.usecases

import com.tmdb.domain.repository.MovieDetailsRepository

/**
 * Created by van.luong
 * on 17,June,2025
 *
 * Use case for getting movie details.
 */
class GetMovieDetailsUseCase(
    private val movieDetailsRepository: MovieDetailsRepository
) {
    operator fun invoke(movieId: Long) =
        movieDetailsRepository.getMovieDetails(movieId)
}