package com.tmdb.database.providers

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.tmdb.database.dao.MovieDetailsDao
import com.tmdb.database.dao.TrendingMoviesDao
import com.tmdb.database.entity.MovieDetailsEntity
import com.tmdb.database.entity.TrendingMovieEntity

/**
 * Created by van.luong
 * on 16,June,2025
 */
@Database(
    entities = [TrendingMovieEntity::class, MovieDetailsEntity::class],
    version = 1
)
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