package com.tmdb.database.mapper

import com.tmdb.database.entity.BelongsToCollectionEntity
import com.tmdb.database.entity.GenreEntity
import com.tmdb.database.entity.MovieDetailWithRelations
import com.tmdb.database.entity.MovieDetailsEntity
import com.tmdb.database.entity.ProductionCompanyEntity
import com.tmdb.database.entity.ProductionCountryEntity
import com.tmdb.database.entity.SpokenLanguageEntity
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
// ---- Single Entity → Model ----
fun MovieDetailsEntity.toModel(
    genres: List<Genre> = emptyList(),
    productionCompanies: List<ProductionCompany> = emptyList(),
    spokenLanguages: List<SpokenLanguage> = emptyList(),
    productionCountries: List<ProductionCountry> = emptyList(),
    belongsToCollection: BelongsToCollection? = null
): MovieDetail = MovieDetail(
    id = id,
    backdropPath = backdropPath,
    originalTitle = originalTitle,
    title = title,
    overview = overview,
    posterPath = posterPath,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    runtime = runtime,
    tagline = tagline,
    status = status,
    homepage = homepage,
    imdbId = imdbId,
    genres = genres,
    productionCompanies = productionCompanies,
    spokenLanguages = spokenLanguages,
    productionCountries = productionCountries,
    belongsToCollection = belongsToCollection
)

fun BelongsToCollectionEntity.toModel(): BelongsToCollection = BelongsToCollection(
    id = collectionId,
    name = name,
    posterPath = posterPath,
    backdropPath = backdropPath
)

fun GenreEntity.toModel(): Genre = Genre(
    id = genreId,
    name = name
)

fun ProductionCompanyEntity.toModel(): ProductionCompany = ProductionCompany(
    id = companyId,
    name = name,
    logoPath = logoPath,
    originCountry = originCountry
)

fun ProductionCountryEntity.toModel(): ProductionCountry = ProductionCountry(
    iso31661 = iso31661,
    name = name
)

fun SpokenLanguageEntity.toModel(): SpokenLanguage = SpokenLanguage(
    englishName = englishName,
    iso6391 = iso6391,
    name = name
)

// -- Relation Mapper
fun MovieDetailWithRelations.toModel(): MovieDetail = movie.toModel(
    genres = genres.map { it.toModel() },
    productionCompanies = productionCompanies.map { it.toModel() },
    spokenLanguages = spokenLanguages.map { it.toModel() },
    productionCountries = productionCountries.map { it.toModel() },
    belongsToCollection = belongsToCollection?.toModel()
)

// ---- Model → Entity ----
fun MovieDetail.toEntity(): MovieDetailsEntity = MovieDetailsEntity(
    id = id,
    adult = false, // optional
    backdropPath = backdropPath ?: "",
    budget = 0, // optional
    homepage = homepage ?: "",
    imdbId = imdbId ?: "",
    originCountry = emptyList(), // ignore or store as needed
    originalLanguage = "", // ignore or store as needed
    originalTitle = originalTitle,
    overview = overview ?: "",
    popularity = 0.0, // optional
    posterPath = posterPath ?: "",
    releaseDate = releaseDate ?: "",
    revenue = 0, // optional
    runtime = runtime ?: 0,
    status = status ?: "",
    tagline = tagline ?: "",
    title = title,
    video = false, // optional
    voteAverage = voteAverage,
    voteCount = 0 // optional
)

fun BelongsToCollection.toEntity(movieId: Long): BelongsToCollectionEntity =
    BelongsToCollectionEntity(
        movieId = movieId,
        collectionId = id,
        name = name,
        posterPath = posterPath,
        backdropPath = backdropPath
    )

fun Genre.toEntity(movieId: Long): GenreEntity = GenreEntity(
    movieId = movieId,
    genreId = id,
    name = name
)

fun ProductionCompany.toEntity(movieId: Long): ProductionCompanyEntity = ProductionCompanyEntity(
    movieId = movieId,
    companyId = id,
    name = name,
    logoPath = logoPath,
    originCountry = originCountry ?: ""
)

fun ProductionCountry.toEntity(movieId: Long): ProductionCountryEntity = ProductionCountryEntity(
    movieId = movieId,
    iso31661 = iso31661,
    name = name
)

fun SpokenLanguage.toEntity(movieId: Long): SpokenLanguageEntity = SpokenLanguageEntity(
    movieId = movieId,
    iso6391 = iso6391,
    englishName = englishName,
    name = name
)


// -- List Mappers for bulk insertion
fun List<Genre>.toEntityList(movieId: Long): List<GenreEntity> = map { it.toEntity(movieId) }
fun List<ProductionCompany>.toEntityList(movieId: Long): List<ProductionCompanyEntity> =
    map { it.toEntity(movieId) }

fun List<ProductionCountry>.toEntityList(movieId: Long): List<ProductionCountryEntity> =
    map { it.toEntity(movieId) }

fun List<SpokenLanguage>.toEntityList(movieId: Long): List<SpokenLanguageEntity> =
    map { it.toEntity(movieId) }

