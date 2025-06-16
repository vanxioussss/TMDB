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

/**
 * Extension function to map the success body of a Resource to a new type.
 *
 * @param transform A function that transforms the success body of type T to type R.
 * @return A new Resource with the transformed body, or the same Resource if it is not successful.
 */
inline fun <T, R> Resource<T>.map(transform: (T) -> R): Resource<R> {
    return when (this) {
        is Resource.Loading -> Resource.Loading
        is Resource.Success -> Resource.Success(transform(this.body))
        is Resource.DataError -> Resource.DataError(this.error)
        is Resource.ServerError -> Resource.ServerError(this.error)
    }
}