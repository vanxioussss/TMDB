package com.tmdb.common.resource

import com.tmdb.common.error.ApiError

/**
 * Created by van.luong
 * on 16,June,2025
 *
 * Resource is a sealed class that represents the state of a resource.
 */
sealed class Resource<out T> {
    data object Loading : Resource<Nothing>()
    data class Success<T>(val body: T) : Resource<T>()
    data class DataError(val error: Throwable) : Resource<Nothing>()
    data class ServerError(val error: ApiError) : Resource<Nothing>()
}