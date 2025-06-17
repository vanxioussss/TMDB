package com.tmbd.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.tmdb.details.ui.MovieDetailScreenRoute
import com.tmdb.movies.ui.MovieHomeScreenRoute
import com.tmdb.navigation.HomeScreenRoute
import com.tmdb.navigation.addDetailsScreen
import com.tmdb.navigation.addHomeScreen

/**
 * Created by van.luong
 * on 16,June,2025
 */
@Composable
fun NavGraphHost(
    modifier: Modifier = Modifier,
    startDestination: Any = HomeScreenRoute,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        addHomeScreen {
            MovieHomeScreenRoute(navController = navController)
        }

        addDetailsScreen { movieId ->
            MovieDetailScreenRoute(movieId = movieId, navController = navController)
        }
    }
}