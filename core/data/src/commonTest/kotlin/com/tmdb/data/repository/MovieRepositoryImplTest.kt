package com.tmdb.data.repository

import app.cash.turbine.test
import com.tmbd.network.dto.MovieDto
import com.tmbd.network.dto.PageDto
import com.tmbd.network.service.MovieDbApiService
import com.tmdb.common.resource.Resource
import com.tmdb.database.dao.TrendingMoviesDao
import com.tmdb.database.entity.TrendingMovieEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.Test

/**
 * Created by van.luong
 * on 17,June,2025
 *
 * The valid cache duration is set to 24 hours (24 * 60 * 60 * 1000 milliseconds).
 * if stale cache is more than 24 hours old, it will fetch from the remote API.
 *
 * this test suite verifies that the repository correctly uses the cache when it is still valid
 */
class MovieRepositoryImplTest {

    private val trendingMoviesDao: TrendingMoviesDao = mockk(relaxed = true)
    private val apiService: MovieDbApiService = mockk(relaxed = true)
    private lateinit var repository: MovieRepositoryImpl

    private val cacheDuration = 24 * 60 * 60 * 1000L

    @Before
    fun setup() {
        repository = MovieRepositoryImpl(apiService, trendingMoviesDao)
    }

    @Test
    fun `returns cached data if cache is still valid`() = runTest {
        val now = System.currentTimeMillis()
        val movieEntity =
            TrendingMovieEntity(
                1L, "Cached Movie", voteAverage = 10f, fetchedAt = now,
                releaseDate = "now",
                posterPath = "mock",
                backdropPath = "mock_backdrop"
            )

        coEvery { trendingMoviesDao.getTrendingFetchTimestamp() } returns now
        coEvery { trendingMoviesDao.getAllTrendingMovies() } returns listOf(movieEntity)

        repository.getTrendingMovie().test {
            awaitItem() // This will emit the loading state

            val result = awaitItem()

            assertTrue(result is Resource.Success)
            assertEquals("Cached Movie", (result as Resource.Success).body.first().originalTitle)

            coVerify(exactly = 0) { apiService.getTrendingMovies(1) }

            awaitComplete()
        }
    }

    @Test
    fun `fetches from remote if cache is expired`() = runTest {
        // Simulate a stale cache by setting the fetch timestamp to more than 10 minutes ago
        val staleTime = System.currentTimeMillis() - (cacheDuration + 1)
        val remoteMovie = PageDto(
            page = 1,
            totalResults = 100,
            totalPages = 10,
            results = listOf(
                MovieDto(
                    id = 123L,
                    originalTitle = "Mock Remote Movie",
                    releaseDate = "2025-01-01",
                    posterPath = "/mockPoster.jpg",
                    backdropPath = "/mockBackdrop.jpg",
                    voteAverage = 8.5f
                ),
                MovieDto(
                    id = 456L,
                    originalTitle = "Another Mock Movie",
                    releaseDate = "2025-02-01",
                    posterPath = "/mockPoster2.jpg",
                    backdropPath = "/mockBackdrop2.jpg",
                    voteAverage = 7.2f
                )
            )
        )

        coEvery { trendingMoviesDao.getTrendingFetchTimestamp() } returns staleTime
        coEvery { apiService.getTrendingMovies(1) } returns Resource.Success(remoteMovie)

        repository.getTrendingMovie().test {
            awaitItem() // This will emit the loading state

            val result = awaitItem()

            assertTrue(result is Resource.Success)
            assertEquals(
                "Mock Remote Movie",
                (result as Resource.Success).body.first().originalTitle
            )

            coVerifySequence {
                trendingMoviesDao.getTrendingFetchTimestamp()
                apiService.getTrendingMovies(1)
                trendingMoviesDao.clearTrendingMovies()
                trendingMoviesDao.insertTrendingMovies(any())
            }

            awaitComplete()
        }
    }
}
