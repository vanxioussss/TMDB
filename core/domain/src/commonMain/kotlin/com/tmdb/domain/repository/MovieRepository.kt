package com.tmdb.domain.repository

import app.cash.paging.PagingSource
import com.tmdb.common.resource.Resource
import com.tmdb.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Created by van.luong
 * on 16,June,2025
 *
 * Repository interface for managing movie-related data operations.
 */
interface MovieRepository {
    /**
     * Searches for movies based on the provided query and page number.
     *
     * @param query The search query for movies.
     * @param page The page number for pagination.
     * @return A flow emitting a Resource containing a page of movie response.
     */
    fun searchMovieBy(query: String, page: Int): PagingSource<Int, Movie>

    /**
     * Retrieves a list of trending movies.
     *
     * @return A flow emitting a Resource containing a list of trending movies.
     */
    suspend fun getTrendingMovie(): Flow<Resource<List<Movie>>>
}