package com.tmbd.android.di

import com.tmdb.BuildConfig
import com.tmdb.bridge.di.domainModule
import com.tmdb.details.di.detailsScreenModule
import com.tmdb.movies.di.homeScreenModule
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by van.luong
 * on 17,June,2025
 */

val authModule = module {
    single(named("api_token")) {
        BuildConfig.MOVIE_DB_API_TOKEN
    }
}

val appModules = listOf(
    authModule,
    domainModule
)

val featuresModule = listOf(
    homeScreenModule,
    detailsScreenModule
)