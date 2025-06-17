package com.tmdb.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute

/**
 * Created by van.luong
 * on 17,June,2025
 *
 * This file contains the navigation graph builder extensions for the home and details screens.
 */
fun NavGraphBuilder.addHomeScreen(content: @Composable AnimatedContentScope.() -> Unit) {
    composable<HomeScreenRoute> {
        content()
    }
}

fun NavGraphBuilder.addDetailsScreen(content: @Composable AnimatedContentScope.(movieId: Long) -> Unit) {
    composable<DetailsScreenRoute> {
        val movieId = it.toRoute<DetailsScreenRoute>().movieId
        content(movieId)
    }
}