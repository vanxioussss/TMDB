package com.tmdb.data.repository

import app.cash.paging.PagingSource
import com.tmbd.network.mapper.toModel
import com.tmbd.network.service.MovieDbApiService
import com.tmdb.common.resource.Resource
import com.tmdb.data.datasource.MoviePagingSource
import com.tmdb.database.dao.TrendingMoviesDao
import com.tmdb.database.mapper.toModelList
import com.tmdb.database.mapper.toTrendingMovieEntityList
import com.tmdb.domain.repository.MovieRepository
import com.tmdb.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.time.LocalDate
import java.time.ZoneId

/**
 * Created by van.luong
 * on 16,June,2025
 *
 * Implementation of [MovieRepository] that handles movie-related data operations.
 */
class MovieRepositoryImpl(
    private val movieDbApiService: MovieDbApiService,
    private val trendingMoviesDao: TrendingMoviesDao
) : MovieRepository {
    override fun searchMovieBy(query: String, page: Int): PagingSource<Int, Movie> {
        return MoviePagingSource(movieDbApiService, query)
    }

    override suspend fun getTrendingMovie(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading)
        val latestCacheTime = trendingMoviesDao.getTrendingFetchTimestamp()
        val cacheValid = latestCacheTime?.let { isCacheStillValid(it) } ?: false

        if (cacheValid) {
            emit(Resource.Success(trendingMoviesDao.getAllTrendingMovies().toModelList()))
        } else {
            when (val newTrendingMovie = movieDbApiService.getTrendingMovies(1, "day")) {
                is Resource.Success -> {
                    val movies = newTrendingMovie.body.results.toModel()
                    trendingMoviesDao.clearTrendingMovies()
                    trendingMoviesDao.insertTrendingMovies(movies.toTrendingMovieEntityList())
                    emit(Resource.Success(movies))
                }

                is Resource.DataError -> {
                    emit(Resource.DataError(newTrendingMovie.error))
                }

                is Resource.Loading -> emit(Resource.Loading)
                is Resource.ServerError -> {
                    emit(Resource.ServerError(newTrendingMovie.error))
                }
            }
        }
    }

    private fun isCacheStillValid(latestCachedTime: Long): Boolean {
        val cachedDate = Instant.fromEpochMilliseconds(latestCachedTime)
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date

        val today = LocalDate.now(ZoneId.systemDefault())
        return cachedDate.equals(today)
    }
}