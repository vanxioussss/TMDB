package com.tmdb.movies.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.tmdb.movies.ui.MovieHomeScreenRoute
import kotlinx.serialization.Serializable

/**
 * Created by van.luong
 * on 16,June,2025
 *
 * Represents the route for the home screen in the movie feature.
 */
@Serializable
data object HomeScreenRoute

fun NavController.navigateToHomeScreen(navOptions: NavOptions?) =
    navigate(route = HomeScreenRoute, navOptions = navOptions)

fun NavGraphBuilder.homeScreen() {
    composable<HomeScreenRoute> {
        MovieHomeScreenRoute()
    }
}