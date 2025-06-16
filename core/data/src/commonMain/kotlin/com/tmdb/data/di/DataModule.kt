package com.tmdb.data.di

/**
 * Created by van.luong
 * on 16,June,2025
 */

//val dataModule =
//    module {
//        includes(networkModule)
//        includes(databaseModule)
//
//        single<MovieRepository> {
//            MovieRepositoryImpl(
//                movieDbApiService = get(),
//                trendingMoviesDao = get(),
//            )
//        }
//
//        factory { (query: String) ->
//            MoviePagingSource(
//                apiService = get(),
//                query = query,
//            )
//        }
//    }