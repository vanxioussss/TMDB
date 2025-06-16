package com.tmdb.data.datasource

import androidx.paging.PagingState
import app.cash.paging.PagingSource
import com.tmbd.network.mapper.toModel
import com.tmbd.network.service.MovieDbApiService
import com.tmdb.common.resource.Resource
import com.tmdb.common.resource.map
import com.tmdb.model.Movie

/**
 * Created by van.luong
 * on 16,June,2025
 *
 * Paging source for loading [Movie] from the repository.
 */
class MoviePagingSource(
    private val apiService: MovieDbApiService,
    private val query: String,
) : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response =
                apiService.searchMovies(query, position).map { it.toModel() }

            when (response) {
                is Resource.Success -> {
                    val moviesPageResponse = response.body
                    val movies = moviesPageResponse.results
                    val totalPages = moviesPageResponse.totalPages

                    val nextKey = if (position < totalPages) position + 1 else null
                    val prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1

                    LoadResult.Page(
                        data = movies,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }

                is Resource.DataError -> LoadResult.Error(response.error)
                is Resource.ServerError -> LoadResult.Error(Exception("Server error: ${response.error.message}"))
                else -> LoadResult.Error(Exception("Unknown error"))
            }
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}