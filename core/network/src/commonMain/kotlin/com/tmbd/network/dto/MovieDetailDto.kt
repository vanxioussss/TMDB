package com.tmbd.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created by van.luong
 * on 16,June,2025
 *
 * Represents a movie detail response from the MovieDB API.
 */
@Serializable
data class MovieDetailDto(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String,
    @SerialName("belongs_to_collection")
    val belongsToCollectionDto: BelongsToCollectionDto,
    val budget: Long,
    val genreDtos: List<GenreDto>,
    val homepage: String,
    val id: Long,
    @SerialName("imdb_id")
    val imdbId: String,
    @SerialName("origin_country")
    val originCountry: List<String>,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanyDto>,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountryDto>,
    @SerialName("release_date")
    val releaseDate: String,
    val revenue: Long,
    val runtime: Long,
    @SerialName("spoken_languages")
    val spokenLanguageDtos: List<SpokenLanguageDto>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Long
)

@Serializable
data class BelongsToCollectionDto(
    val id: Long,
    val name: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("backdrop_path")
    val backdropPath: String,
)

@Serializable
data class GenreDto(
    val id: Long,
    val name: String,
)

@Serializable
data class ProductionCompanyDto(
    val id: Long,
    @SerialName("logo_path")
    val logoPath: String,
    val name: String,
    @SerialName("origin_country")
    val originCountry: String,
)

@Serializable
data class ProductionCountryDto(
    @SerialName("iso_3166_1")
    val iso31661: String,
    val name: String,
)

@Serializable
data class SpokenLanguageDto(
    @SerialName("english_name")
    val englishName: String,
    @SerialName("iso_639_1")
    val iso6391: String,
    val name: String,
)