package com.example.animeapp

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.animeapp.components.search.SearchScreenContent
import com.example.domain.model.search.Anime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchScreenContentUITest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Composable
    fun testScreen(animeData: Flow<PagingData<Anime>>) {
        val animeItems = animeData.collectAsLazyPagingItems()
        SearchScreenContent(
            animes = animeItems,
            onTypeChanged = { /* ... */ },
            onSortChanged = { /* ... */ },
            onSearchChanged = { /* ... */ },
            onAnimeSelected = { /* ... */ },
            onToggleFavorite = { /* ... */ },
            favoriteAnime = setOf(/* ... */)
        )
    }

    @Test
    fun testAnimeListShowsCorrectData() {
        val animes = flowOf(
            PagingData.from(
                listOf(
                    Anime(
                        id = 1,
                        title = "Demon Slayer",
                        imageUrl = "https://i.blogs.es/bc1dd2/naruto/840_560.png"
                    ),
                    Anime(
                        id = 2,
                        title = "One Piece",
                        imageUrl = "https://example.com/one_piece.png"
                    )
                )
            )
        )

        composeTestRule.setContent {
            testScreen(animes)
        }

        composeTestRule.onNodeWithText("Demon Slayer").assertExists()
        composeTestRule.onNodeWithText("One Piece").assertExists()
    }

    @Test
    fun testOnTypeChanged() {
        // Create the test data
        val animes = flowOf(
            PagingData.from(
                listOf(
                    Anime(
                        id = 1,
                        title = "Demon Slayer",
                        imageUrl = "https://i.blogs.es/bc1dd2/naruto/840_560.png"
                    ),
                    Anime(
                        id = 2,
                        title = "One Piece",
                        imageUrl = "https://example.com/one_piece.png"
                    )
                )
            )
        )

        composeTestRule.setContent {
            testScreen(animes)
        }

        // Click on the dropdown menu identified by the test tag
        composeTestRule.onNodeWithTag("typeDropdown").performClick()

        // Select 'MANGA' from the dropdown options
        composeTestRule.onNodeWithText("MANGA").performClick()

        // Assert that the selected type is updated
        // It should now display 'MANGA' as the selected type
        composeTestRule.onNodeWithText("MANGA").assertExists()
    }
}

