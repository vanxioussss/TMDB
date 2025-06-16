package com.tmdb.domain.repository

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
    suspend fun searchMovieBy(query: String, page: Int): Flow<Resource<List<Movie>>>

    suspend fun getTrendingMovie(): Flow<Resource<List<Movie>>>
}