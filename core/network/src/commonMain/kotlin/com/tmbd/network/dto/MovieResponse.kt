package com.tmbd.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by van.luong
 * on 16,June,2025
 *
 * Represents a movie response from the MovieDB API.
 */
@Serializable
data class MovieResponse(
    val id: Long,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("original_title")
    val originalTitle: String,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("vote_average")
    val voteAverage: Float?,
)