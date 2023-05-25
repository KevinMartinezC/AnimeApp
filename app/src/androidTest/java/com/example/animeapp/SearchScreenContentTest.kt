package com.example.animeapp

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
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
}

