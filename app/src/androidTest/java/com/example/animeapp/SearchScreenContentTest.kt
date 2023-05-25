package com.example.animeapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher.Companion.keyIsDefined
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.animeapp.components.search.SearchScreenContent
import com.example.domain.model.search.Anime
import com.example.domain.model.search.AnimeType
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchScreenContentUITest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

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
            val animeItems = animes.collectAsLazyPagingItems()
            SearchScreenContent(
                animes = animeItems,
                onTypeChanged = { },
                onSortChanged = { },
                onSearchChanged = { },
                onAnimeSelected = { },
                onToggleFavorite = { },
                favoriteAnime = setOf()
            )
        }

        composeTestRule.onNodeWithText("Demon Slayer").assertExists()
        composeTestRule.onNodeWithText("One Piece").assertExists()
    }

    @Test
    fun testOnTypeChanged() {

        var selectedType: AnimeType? = null
        val onTypeChanged: (AnimeType) -> Unit = {
            selectedType = it
        }

        composeTestRule.setContent {
            val animeItems = flowOf(PagingData.empty<Anime>()).collectAsLazyPagingItems()
            SearchScreenContent(
                animes = animeItems,
                onTypeChanged = onTypeChanged,
                onSortChanged = { },
                onSearchChanged = { },
                onAnimeSelected = { },
                onToggleFavorite = { },
                favoriteAnime = setOf()
            )
        }

        composeTestRule.onNodeWithTag("typeDropdown")
            .performClick()

        // Interact with options
        composeTestRule
            .onNode(hasText(AnimeType.MANGA.toString()).and(hasAnyAncestor(keyIsDefined(
                SemanticsProperties.IsPopup))))
            .performClick()

        // Verify
        assertEquals(AnimeType.MANGA, selectedType)
    }

}

