package com.tmdb.navigation

import kotlinx.serialization.Serializable

/**
 * Created by van.luong
 * on 17,June,2025
 *
 * Represents the routes in the navigation graph.
 */
@Serializable
data object HomeScreenRoute

@Serializable
data class DetailsScreenRoute(val movieId: Long)