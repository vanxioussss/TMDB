package com.tmdb.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

/**
 * Created by van.luong
 * on 16,June,2025
 *
 * Entity representing the details of a movie.
 */
@Entity(tableName = "movie_details")
data class MovieDetailsEntity(
    @PrimaryKey val id: Long,
    val adult: Boolean,
    val backdropPath: String,
    val budget: Long,
    val homepage: String,
    val imdbId: String,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Long,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long
)

data class MovieDetailWithRelations(
    @Embedded val movie: MovieDetailsEntity,

    @Relation(parentColumn = "id", entityColumn = "movieId")
    val genres: List<GenreEntity> = emptyList(),

    @Relation(parentColumn = "id", entityColumn = "movieId")
    val productionCompanies: List<ProductionCompanyEntity> = emptyList(),

    @Relation(parentColumn = "id", entityColumn = "movieId")
    val spokenLanguages: List<SpokenLanguageEntity> = emptyList(),

    @Relation(parentColumn = "id", entityColumn = "movieId")
    val productionCountries: List<ProductionCountryEntity> = emptyList(),

    @Relation(parentColumn = "id", entityColumn = "movieId")
    val belongsToCollection: BelongsToCollectionEntity? = null
)

@Entity(
    tableName = "movie_belongs_to_collection",
    primaryKeys = ["movieId"],
    foreignKeys = [
        ForeignKey(
            entity = MovieDetailsEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class BelongsToCollectionEntity(
    val movieId: Long,
    val collectionId: Long,
    val name: String,
    val posterPath: String?,
    val backdropPath: String?
)

@Entity(
    tableName = "movie_genres",
    primaryKeys = ["movieId", "genreId"],
    foreignKeys = [
        ForeignKey(
            entity = MovieDetailsEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class GenreEntity(
    val movieId: Long,
    val genreId: Long,
    val name: String
)

@Entity(
    tableName = "movie_production_companies",
    primaryKeys = ["movieId", "companyId"],
    foreignKeys = [
        ForeignKey(
            entity = MovieDetailsEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ProductionCompanyEntity(
    val movieId: Long,
    val companyId: Long,
    val name: String,
    val logoPath: String?,
    val originCountry: String
)

@Entity(
    tableName = "movie_production_countries",
    primaryKeys = ["movieId", "iso31661"],
    foreignKeys = [
        ForeignKey(
            entity = MovieDetailsEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ProductionCountryEntity(
    val movieId: Long,
    val iso31661: String,
    val name: String
)

@Entity(
    tableName = "movie_spoken_languages",
    primaryKeys = ["movieId", "iso6391"],
    foreignKeys = [
        ForeignKey(
            entity = MovieDetailsEntity::class,
            parentColumns = ["id"],
            childColumns = ["movieId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SpokenLanguageEntity(
    val movieId: Long,
    val iso6391: String,
    val englishName: String,
    val name: String
)