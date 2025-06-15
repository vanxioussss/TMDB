package com.tmbd.network.mapper

import com.tmbd.network.dto.BelongsToCollectionDto
import com.tmbd.network.dto.GenreDto
import com.tmbd.network.dto.MovieDetailDto
import com.tmbd.network.dto.ProductionCompanyDto
import com.tmbd.network.dto.ProductionCountryDto
import com.tmbd.network.dto.SpokenLanguageDto
import com.tmdb.model.BelongsToCollection
import com.tmdb.model.Genre
import com.tmdb.model.MovieDetail
import com.tmdb.model.ProductionCompany
import com.tmdb.model.ProductionCountry
import com.tmdb.model.SpokenLanguage

/**
 * Created by van.luong
 * on 16,June,2025
 */

// ---- Dto -> Model Mappers ----
fun MovieDetailDto.toModel(): MovieDetail {
    return MovieDetail(
        id = id,
        title = title,
        originalTitle = originalTitle,
        overview = overview,
        releaseDate = releaseDate,
        posterPath = posterPath,
        backdropPath = backdropPath,
        voteAverage = voteAverage,
        runtime = runtime,
        homepage = homepage,
        imdbId = imdbId,
        status = status,
        tagline = tagline,
        belongsToCollection = belongsToCollectionDto.toModel(),
        genres = genreDtos.map { it.toModel() },
        productionCompanies = productionCompanies.map { it.toModel() },
        productionCountries = productionCountries.map { it.toModel() },
        spokenLanguages = spokenLanguageDtos.map { it.toModel() }
    )
}

fun BelongsToCollectionDto.toModel(): BelongsToCollection {
    return BelongsToCollection(
        id = id,
        name = name,
        posterPath = posterPath,
        backdropPath = backdropPath
    )
}

fun GenreDto.toModel(): Genre {
    return Genre(
        id = id,
        name = name
    )
}

fun ProductionCompanyDto.toModel(): ProductionCompany {
    return ProductionCompany(
        id = id,
        name = name,
        logoPath = logoPath,
        originCountry = originCountry
    )
}

fun ProductionCountryDto.toModel(): ProductionCountry {
    return ProductionCountry(
        iso31661 = iso31661,
        name = name
    )
}

fun SpokenLanguageDto.toModel(): SpokenLanguage {
    return SpokenLanguage(
        englishName = englishName,
        iso6391 = iso6391,
        name = name
    )
}

// ---- List Mappers ----

fun List<GenreDto>.toModel(): List<Genre> = map { it.toModel() }

fun List<ProductionCompanyDto>.toModel(): List<ProductionCompany> = map { it.toModel() }

fun List<ProductionCountryDto>.toModel(): List<ProductionCountry> = map { it.toModel() }

fun List<SpokenLanguageDto>.toModel(): List<SpokenLanguage> = map { it.toModel() }

// ---- Model -> Dto Mappers ----

fun MovieDetail.toDto(): MovieDetailDto = MovieDetailDto(
    adult = false, // or map if you need it
    backdropPath = backdropPath ?: "",
    belongsToCollectionDto = belongsToCollection?.toDto() ?: BelongsToCollectionDto(0, "", "", ""),
    budget = 0, // or store if needed
    genreDtos = genres.toDto(),
    homepage = homepage ?: "",
    id = id,
    imdbId = imdbId ?: "",
    originCountry = emptyList(), // TMDB detail doesn't provide, ignore or store if needed
    originalLanguage = "", // optional to store if needed
    originalTitle = originalTitle,
    overview = overview ?: "",
    popularity = 0.0, // optional
    posterPath = posterPath ?: "",
    productionCompanies = productionCompanies.toDto(),
    productionCountries = productionCountries.toDto(),
    releaseDate = releaseDate ?: "",
    revenue = 0, // optional
    runtime = runtime ?: 0,
    spokenLanguageDtos = spokenLanguages.toDto(),
    status = status ?: "",
    tagline = tagline ?: "",
    title = title,
    video = false, // optional
    voteAverage = voteAverage,
    voteCount = 0 // optional
)

fun BelongsToCollection.toDto(): BelongsToCollectionDto = BelongsToCollectionDto(
    id = id,
    name = name,
    posterPath = posterPath ?: "",
    backdropPath = backdropPath ?: ""
)

fun Genre.toDto(): GenreDto = GenreDto(
    id = id,
    name = name
)

fun ProductionCompany.toDto(): ProductionCompanyDto = ProductionCompanyDto(
    id = id,
    logoPath = logoPath ?: "",
    name = name,
    originCountry = originCountry ?: ""
)

fun ProductionCountry.toDto(): ProductionCountryDto = ProductionCountryDto(
    iso31661 = iso31661,
    name = name
)

fun SpokenLanguage.toDto(): SpokenLanguageDto = SpokenLanguageDto(
    englishName = englishName,
    iso6391 = iso6391,
    name = name
)


// ---- List Mappers ----

fun List<Genre>.toDto(): List<GenreDto> = map { it.toDto() }

fun List<ProductionCompany>.toDto(): List<ProductionCompanyDto> = map { it.toDto() }

fun List<ProductionCountry>.toDto(): List<ProductionCountryDto> = map { it.toDto() }

fun List<SpokenLanguage>.toDto(): List<SpokenLanguageDto> = map { it.toDto() }
