package com.tmdb.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.tmdb.database.entity.BelongsToCollectionEntity
import com.tmdb.database.entity.GenreEntity
import com.tmdb.database.entity.MovieDetailWithRelations
import com.tmdb.database.entity.MovieDetailsEntity
import com.tmdb.database.entity.ProductionCompanyEntity
import com.tmdb.database.entity.SpokenLanguageEntity

/**
 * Created by van.luong
 * on 16,June,2025
 */
@Dao
interface MovieDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(movie: MovieDetailsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenres(genres: List<GenreEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductionCompanies(companies: List<ProductionCompanyEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductionCountries(countries: List<ProductionCompanyEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpokenLanguages(languages: List<SpokenLanguageEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBelongsToCollection(collection: BelongsToCollectionEntity)

    @Transaction
    @Query("SELECT * FROM movie_details WHERE id = :movieId")
    suspend fun getMovieDetailWithRelations(movieId: Long): MovieDetailWithRelations?
}