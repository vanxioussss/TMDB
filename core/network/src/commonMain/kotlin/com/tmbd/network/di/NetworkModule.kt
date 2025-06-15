package com.tmbd.network.di

import com.tmbd.network.providers.httpClientEngine
import com.tmbd.network.service.MovieDbApiService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by van.luong
 * on 16,June,2025
 */
val networkModule =
    module {
        single {
            HttpClient(
                engine = httpClientEngine()
            ) {
                install(Logging) {
                    logger = Logger.SIMPLE
                    level = LogLevel.HEADERS
                }

                install(ContentNegotiation) {
                    json(
                        json =
                            Json {
                                ignoreUnknownKeys = true
                            },
                    )
                }

                install(Auth) {
                    bearer {
                        loadTokens {
                            BearerTokens(
                                get(named("api_token")),
                                "",
                            )
                        }
                    }
                }
            }
        }

        single {
            MovieDbApiService(
                baseUrl = "https://api.themoviedb.org/3",
                httpClient = get(),
            )
        }
    }