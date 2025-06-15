package com.tmbd.network.service

import com.tmbd.network.dto.MovieDetailDto
import com.tmbd.network.dto.PageDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

/**
 * Created by van.luong
 * on 16,June,2025
 *
 * Service for interacting with the Movie Database API.
 */
class MovieDbApiService(
    private val baseUrl: String,
    private val httpClient: HttpClient
) {
    /**
     * Get a list of trending movies based on time window.
     *
     * @param pageNumber The page number to retrieve.
     * @param timeWindow The time window for trending movies, default is "day".
     */
    suspend fun getTrendingMovies(pageNumber: Int, timeWindow: String = "day"): PageDto =
        fetchData("trending/movie/$timeWindow?page=$pageNumber")

    /**
     * Search for movies by a query string.
     *
     * @param query The search query string.
     * @param pageNumber The page number to retrieve.
     */
    suspend fun searchMovies(query: String, pageNumber: Int): PageDto =
        fetchData("search/movie?query=$query&page=$pageNumber")

    /**
     * Get movie details by movie ID.
     *
     * @param movieId The ID of the movie to retrieve details for.
     */
    suspend fun getMovieDetails(movieId: Long): MovieDetailDto =
        fetchMovie("$movieId")

    private suspend inline fun <reified T> fetchMovie(suffix: String): T =
        fetchData("movie/$suffix")

    private suspend inline fun <reified T> fetchData(endpoint: String): T =
        httpClient.get("$baseUrl/$endpoint").body()
}