package com.tmdb.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by van.luong
 * on 16,June,2025
 *
 * Entity representing a trending movie in the database.
 */
@Entity(tableName = "trending_movies")
data class TrendingMovieEntity(
    @PrimaryKey val id: Long,
    val originalTitle: String,
    val releaseDate: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double,
    val fetchedAt: Long // timestamp in millis
)
