package com.tmdb.movies

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tmdb.movies.state.MovieUiState
import com.tmdb.movies.ui.MovieHomeScreen
import com.tmdb.testing.MockDataUtil
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by van.luong
 * on 17,June,2025
 *
 * Instrumented test for [MovieHomeScreen] using Jetpack Compose.
 */
@RunWith(AndroidJUnit4::class)
class MovieScreenComposeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showsTrendingMovies_whenTrendingUiStateProvided() {
        val movies = MockDataUtil.mockMovieList()

        composeTestRule.setContent {
            MovieHomeScreen(
                movieUiState = MovieUiState.ShowTrending(movies),
                searchQuery = "",
                onQueryChange = {},
                onMovieClick = {}
            )
        }

        composeTestRule.onNodeWithText("Trending Movies").assertIsDisplayed()
        composeTestRule.onNodeWithText("Mock Remote Movie").assertIsDisplayed()
        composeTestRule.onNodeWithText("Another Mock Movie").assertIsDisplayed()
    }

    @Test
    fun showsLoadingIndicator_whenLoadingUiStateProvided() {
        composeTestRule.setContent {
            MovieHomeScreen(
                movieUiState = MovieUiState.Loading,
                searchQuery = "",
                onQueryChange = {},
                onMovieClick = {}
            )
        }

        composeTestRule.onNodeWithTag("FullScreenLoading").assertIsDisplayed()
    }

    @Test
    fun showsErrorMessage_whenErrorUiStateProvided() {
        val errorMessage = "Something went wrong"

        composeTestRule.setContent {
            MovieHomeScreen(
                movieUiState = MovieUiState.Error(errorMessage),
                searchQuery = "",
                onQueryChange = {},
                onMovieClick = {}
            )
        }

        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun searchQuery_updates_whenTyping() {
        var query = ""
        composeTestRule.setContent {
            MovieHomeScreen(
                movieUiState = MovieUiState.Loading,
                searchQuery = query,
                onQueryChange = { query = it },
                onMovieClick = {}
            )
        }

        val input = "Batman"
        composeTestRule.onNodeWithTag("SearchTextField").performTextInput(input)

        assert(query == input)
    }
}