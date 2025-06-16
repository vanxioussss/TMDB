package com.tmdb.common.error

/**
 * Created by van.luong
 * on 16,June,2025
 *
 * Interface for mapping API errors to a common ApiError type.
 */
interface ApiErrorMapper {
    fun mapApiError(e: Throwable): ApiError
}