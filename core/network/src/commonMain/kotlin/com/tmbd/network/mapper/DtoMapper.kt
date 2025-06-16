package com.tmbd.network.mapper

import com.tmbd.network.dto.BelongsToCollectionDto
import com.tmbd.network.dto.GenreDto
import com.tmbd.network.dto.MovieDetailDto
import com.tmbd.network.dto.MovieDto
import com.tmbd.network.dto.PageDto
import com.tmbd.network.dto.ProductionCompanyDto
import com.tmbd.network.dto.ProductionCountryDto
import com.tmbd.network.dto.SpokenLanguageDto
import com.tmdb.model.BelongsToCollection
import com.tmdb.model.Genre
import com.tmdb.model.Movie
import com.tmdb.model.MovieDetail
import com.tmdb.model.MoviePageResponse
import com.tmdb.model.ProductionCompany
import com.tmdb.model.ProductionCountry
import com.tmdb.model.SpokenLanguage

/**
 * Created by van.luong
 * on 16,June,2025
 */

private const val BASE_URL = "https://image.tmdb.org/t/p/w"
const val BIG_SIZE_IMAGE_ADDRESS = "${BASE_URL}780"
const val SMALL_SIZE_IMAGE_ADDRESS = "${BASE_URL}300"

private fun String.toSmallImageUrl(): String = SMALL_SIZE_IMAGE_ADDRESS.plus(this)
private fun String.toBigImageUrl(): String = BIG_SIZE_IMAGE_ADDRESS.plus(this)

// ---- Dto -> Model Mappers ----
fun MovieDto.toModel(): Movie {
    return Movie(
        id = id,
        originalTitle = originalTitle,
        releaseDate = releaseDate,
        posterPath = posterPath?.toSmallImageUrl(),
        backdropPath = backdropPath?.toBigImageUrl(),
        voteAverage = voteAverage,
    )
}

fun PageDto.toModel(): MoviePageResponse {
    return MoviePageResponse(
        page = page,
        totalResults = totalResults,
        totalPages = totalPages,
        results = results.toMovieModel()
    )
}

fun MovieDetailDto.toModel(): MovieDetail {
    return MovieDetail(
        id = id,
        title = title,
        originalTitle = originalTitle,
        overview = overview,
        releaseDate = releaseDate,
        posterPath = posterPath.toSmallImageUrl(),
        backdropPath = backdropPath.toBigImageUrl(),
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
fun List<MovieDto>.toMovieModel(): List<Movie> = map { it.toModel() }

fun List<GenreDto>.toGenreModel(): List<Genre> = map { it.toModel() }

fun List<ProductionCompanyDto>.toProductionCompanyModel(): List<ProductionCompany> =
    map { it.toModel() }

fun List<ProductionCountryDto>.toProductionCountryModel(): List<ProductionCountry> =
    map { it.toModel() }

fun List<SpokenLanguageDto>.toSpokenLanguageModel(): List<SpokenLanguage> = map { it.toModel() }

// ---- Model -> Dto Mappers ----
fun Movie.toDto(): MovieDto = MovieDto(
    id = id,
    originalTitle = originalTitle,
    releaseDate = releaseDate ?: "",
    posterPath = posterPath ?: "",
    backdropPath = backdropPath ?: "",
    voteAverage = voteAverage
)

fun MoviePageResponse.toDto(): PageDto = PageDto(
    page = page,
    totalResults = totalResults,
    totalPages = totalPages,
    results = results.toMovieDto()
)

fun MovieDetail.toDto(): MovieDetailDto = MovieDetailDto(
    adult = false, // or map if you need it
    backdropPath = backdropPath ?: "",
    belongsToCollectionDto = belongsToCollection?.toDto() ?: BelongsToCollectionDto(0, "", "", ""),
    budget = 0, // or store if needed
    genreDtos = genres.toGenreDto(),
    homepage = homepage ?: "",
    id = id,
    imdbId = imdbId ?: "",
    originCountry = emptyList(), // TMDB detail doesn't provide, ignore or store if needed
    originalLanguage = "", // optional to store if needed
    originalTitle = originalTitle,
    overview = overview ?: "",
    popularity = 0.0, // optional
    posterPath = posterPath ?: "",
    productionCompanies = productionCompanies.toProductionCompanyDto(),
    productionCountries = productionCountries.toProductionCountryDto(),
    releaseDate = releaseDate ?: "",
    revenue = 0, // optional
    runtime = runtime ?: 0,
    spokenLanguageDtos = spokenLanguages.toSpokenLanguageDto(),
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
fun List<Movie>.toMovieDto(): List<MovieDto> = map { it.toDto() }

fun List<Genre>.toGenreDto(): List<GenreDto> = map { it.toDto() }

fun List<ProductionCompany>.toProductionCompanyDto(): List<ProductionCompanyDto> =
    map { it.toDto() }

fun List<ProductionCountry>.toProductionCountryDto(): List<ProductionCountryDto> =
    map { it.toDto() }

fun List<SpokenLanguage>.toSpokenLanguageDto(): List<SpokenLanguageDto> = map { it.toDto() }
