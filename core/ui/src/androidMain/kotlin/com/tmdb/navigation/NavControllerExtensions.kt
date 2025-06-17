package com.tmdb.navigation

import androidx.navigation.NavHostController

/**
 * Created by van.luong
 * on 17,June,2025
 *
 * Extension functions for [NavHostController] to navigate between screens.
 */
fun NavHostController.navigateToDetails(movieId: Long) {
    navigate(DetailsScreenRoute(movieId))
}

fun NavHostController.navigateToHome() {
    val result = popBackStack(HomeScreenRoute, inclusive = false)
    if (result.not()) {
        navigate(HomeScreenRoute)
    }
}