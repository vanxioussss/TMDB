package com.tmdb.data.repository

import com.tmbd.network.mapper.toModel
import com.tmbd.network.service.MovieDbApiService
import com.tmdb.common.resource.Resource
import com.tmdb.database.dao.MovieDetailsDao
import com.tmdb.database.entity.MovieDetailWithRelations
import com.tmdb.database.mapper.toEntity
import com.tmdb.database.mapper.toModel
import com.tmdb.domain.repository.MovieDetailsRepository
import com.tmdb.model.MovieDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by van.luong
 * on 17,June,2025
 */
class MovieDetailsRepositoryImpl(
    private val movieDbApiService: MovieDbApiService,
    private val movieDetailsDao: MovieDetailsDao
) : MovieDetailsRepository {

    override fun getMovieDetails(movieId: Long): Flow<Resource<MovieDetail>> = flow {
        emit(Resource.Loading)

        val cachedMovie = movieDetailsDao.getMovieDetailWithRelations(movieId)
        if (cachedMovie != null) {
            // Should have checked if the cached movie is stale, but for simplicity, we assume it's fresh.
            emit(Resource.Success(cachedMovie.movie.toModel()))
            return@flow
        }

        when (val movieDetailsResponse = movieDbApiService.getMovieDetails(movieId)) {
            is Resource.Success -> {
                val movieDetails = movieDetailsResponse.body.toModel()
                val updatedCachedMovie = insertMovieDetailWithRelations(movieDetails, movieId)
                if (updatedCachedMovie == null) {
                    emit(Resource.DataError(Exception("Failed to insert movie details into the database.")))
                    return@flow
                }

                emit(Resource.Success(updatedCachedMovie.toModel()))
            }

            is Resource.DataError -> {
                emit(Resource.DataError(movieDetailsResponse.error))
            }

            is Resource.ServerError -> {
                emit(Resource.ServerError(movieDetailsResponse.error))
            }

            is Resource.Loading -> {
                // This case should not happen here, but we handle it just in case.
                emit(Resource.Loading)
            }
        }

    }.flowOn(Dispatchers.IO)

    private suspend fun insertMovieDetailWithRelations(
        movieDetails: MovieDetail,
        movieId: Long
    ): MovieDetailWithRelations? {
        val entity = movieDetails.toEntity()
        movieDetailsDao.insertMovieDetail(entity)
        movieDetailsDao.insertGenres(movieDetails.genres.map { it.toEntity(movieId) })
        movieDetailsDao.insertProductionCompanies(movieDetails.productionCompanies.map {
            it.toEntity(
                movieId
            )
        })
        movieDetailsDao.insertProductionCountries(movieDetails.productionCountries.map {
            it.toEntity(
                movieId
            )
        })
        movieDetailsDao.insertSpokenLanguages(movieDetails.spokenLanguages.map { it.toEntity(movieId) })
        movieDetails.belongsToCollection?.let {
            movieDetailsDao.insertBelongsToCollection(it.toEntity(movieId))
        }

        return movieDetailsDao.getMovieDetailWithRelations(movieId)
    }
}