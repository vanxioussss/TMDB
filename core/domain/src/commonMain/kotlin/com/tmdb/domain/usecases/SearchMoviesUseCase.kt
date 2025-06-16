package com.tmdb.domain.usecases

import androidx.paging.PagingConfig
import app.cash.paging.Pager
import com.tmdb.domain.repository.MovieRepository

/**
 * Created by van.luong
 * on 16,June,2025
 *
 * Use case for searching movies.
 */
class SearchMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(query: String) = Pager(
        config = PagingConfig(
            pageSize = 20, // arbitrary, not used by API
            enablePlaceholders = false
        ),
        pagingSourceFactory = { movieRepository.searchMovieBy(query, 1) }
    ).flow
}