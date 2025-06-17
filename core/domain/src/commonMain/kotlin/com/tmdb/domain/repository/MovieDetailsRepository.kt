package com.tmdb.domain.repository

import com.tmdb.common.resource.Resource
import com.tmdb.model.MovieDetail
import kotlinx.coroutines.flow.Flow

/**
 * Created by van.luong
 * on 16,June,2025
 */
interface MovieDetailsRepository {
    fun getMovieDetails(movieId: Long): Flow<Resource<MovieDetail>>
}