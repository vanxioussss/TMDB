package com.tmbd.network.service

import com.tmbd.network.dto.MovieDetailDto
import com.tmbd.network.dto.PageDto
import com.tmbd.network.mapper.NetworkApiErrorMapper
import com.tmdb.common.error.ApiErrorMapper
import com.tmdb.common.resource.Resource
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
    suspend fun getTrendingMovies(pageNumber: Int, timeWindow: String = "day"): Resource<PageDto> =
        fetchData("trending/movie/$timeWindow?page=$pageNumber")

    /**
     * Search for movies by a query string.
     *
     * @param query The search query string.
     * @param pageNumber The page number to retrieve.
     */
    suspend fun searchMovies(query: String, pageNumber: Int): Resource<PageDto> =
        fetchData("search/movie?query=$query&page=$pageNumber")

    /**
     * Get movie details by movie ID.
     *
     * @param movieId The ID of the movie to retrieve details for.
     */
    suspend fun getMovieDetails(movieId: Long): Resource<MovieDetailDto> =
        fetchMovie("$movieId")

    private suspend inline fun <reified T> fetchMovie(suffix: String): Resource<T> =
        fetchData("movie/$suffix")

    private suspend inline fun <reified T> fetchData(endpoint: String): Resource<T> =
        httpClient.safeGet("$baseUrl/$endpoint")
}

/**
 * Extension function for HttpClient to safely perform a GET request and handle errors.
 *
 * @param url The URL to fetch data from.
 * @param errorMapper An optional error mapper to handle API errors.
 * @return A [Resource] containing the result of the GET request.
 */
suspend inline fun <reified T> HttpClient.safeGet(
    url: String,
    errorMapper: ApiErrorMapper = NetworkApiErrorMapper()
): Resource<T> {
    return try {
        val response = get(url).body<T>()
        Resource.Success(response)
    } catch (e: Throwable) {
        val apiError = errorMapper.mapApiError(e)
        Resource.ServerError(apiError)
    }
}