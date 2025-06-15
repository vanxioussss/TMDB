package com.tmbd.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by van.luong
 * on 16,June,2025
 *
 * Represents a paginated response from the MovieDB API.
 */
@Serializable
data class PageResponse(
    val page: Int,
    @SerialName("total_results")
    val totalResults: Int,
    @SerialName("total_pages")
    val totalPages: Int,
    val results: List<MovieResponse> = emptyList(),
)