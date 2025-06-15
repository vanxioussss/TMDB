package com.tmdb.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tmdb.database.entity.TrendingMovieEntity

/**
 * Created by van.luong
 * on 16,June,2025
 */
@Dao
interface TrendingMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrendingMovies(movies: List<TrendingMovieEntity>)

    @Query("DELETE FROM trending_movies")
    suspend fun clearTrendingMovies()

    @Query("SELECT * FROM trending_movies")
    suspend fun getAllTrendingMovies(): List<TrendingMovieEntity>

    @Query("SELECT MAX(fetchedAt) FROM trending_movies")
    suspend fun getTrendingFetchTimestamp(): Long?
}