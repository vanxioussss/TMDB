package com.tmdb.model

/**
 * Created by van.luong
 * on 16,June,2025
 *
 * Data class representing the details of a movie.
 */
data class MovieDetail(
    val id: Long,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val releaseDate: String,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double,
    val runtime: Long?,
    val homepage: String?,
    val imdbId: String?,
    val status: String?,
    val tagline: String?,
    val belongsToCollection: BelongsToCollection?,
    val genres: List<Genre>,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val spokenLanguages: List<SpokenLanguage>
)

data class BelongsToCollection(
    val id: Long,
    val name: String,
    val posterPath: String?,
    val backdropPath: String?
)

data class Genre(
    val id: Long,
    val name: String
)

data class ProductionCompany(
    val id: Long,
    val name: String,
    val logoPath: String?,
    val originCountry: String
)

data class ProductionCountry(
    val iso31661: String,
    val name: String
)

data class SpokenLanguage(
    val englishName: String,
    val iso6391: String,
    val name: String
)

