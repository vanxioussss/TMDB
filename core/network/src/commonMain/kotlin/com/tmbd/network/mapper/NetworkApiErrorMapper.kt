package com.tmbd.network.mapper

import com.tmdb.common.error.ApiError
import com.tmdb.common.error.ApiErrorMapper
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.io.IOException

/**
 * Created by van.luong
 * on 16,June,2025
 */
class NetworkApiErrorMapper : ApiErrorMapper {
    override fun mapApiError(e: Throwable): ApiError {
        return when (e) {
            is IOException -> ApiError.Network("Network Connection issue")

            is RedirectResponseException -> ApiError.Unexpected("Redirect error: ${e.response.status.value} ${e.response.status.description}")

            is ClientRequestException -> {
                val code = e.response.status.value
                ApiError.Client("Client error: ${e.response.status.description}", code)
            }

            is ServerResponseException -> {
                val code = e.response.status.value
                ApiError.Server("Server error: ${e.response.status.description}", code)
            }

            is ResponseException -> {
                // fallback for unknown ResponseExceptions
                ApiError.Unexpected("Unexpected HTTP error: ${e.response.status.value} ${e.response.status.description}")
            }

            else -> ApiError.Unexpected("Unexpected error: ${e.localizedMessage ?: "Unknown"}")
        }
    }
}