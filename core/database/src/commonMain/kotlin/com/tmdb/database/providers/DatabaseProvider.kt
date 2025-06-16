package com.tmdb.database.providers

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.tmdb.database.converters.StringListTypeConverter
import com.tmdb.database.dao.MovieDetailsDao
import com.tmdb.database.dao.TrendingMoviesDao
import com.tmdb.database.entity.BelongsToCollectionEntity
import com.tmdb.database.entity.GenreEntity
import com.tmdb.database.entity.MovieDetailsEntity
import com.tmdb.database.entity.ProductionCompanyEntity
import com.tmdb.database.entity.ProductionCountryEntity
import com.tmdb.database.entity.SpokenLanguageEntity
import com.tmdb.database.entity.TrendingMovieEntity

/**
 * Created by van.luong
 * on 16,June,2025
 */
@Database(
    entities = [
        TrendingMovieEntity::class,
        MovieDetailsEntity::class,
        BelongsToCollectionEntity::class,
        GenreEntity::class,
        ProductionCompanyEntity::class,
        ProductionCountryEntity::class,
        SpokenLanguageEntity::class],
    version = 1
)
@TypeConverters(StringListTypeConverter::class)
@ConstructedBy(TMDBDatabaseConstructor::class)
abstract class TMDBDatabase : RoomDatabase() {
    abstract fun trendingMoviesDao(): TrendingMoviesDao

    abstract fun movieDetailsDao(): MovieDetailsDao
}

internal const val dbFileName = "movieDBApp.db"

expect fun databaseInstance(): TMDBDatabase

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object TMDBDatabaseConstructor : RoomDatabaseConstructor<TMDBDatabase> {
    override fun initialize(): TMDBDatabase
}