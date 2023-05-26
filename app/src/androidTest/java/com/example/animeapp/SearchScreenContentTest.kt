package com.example.animeapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher.Companion.keyIsDefined
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.paging.PagingData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.animeapp.components.search.utils.AnimeSortUtils
import com.example.animeapp.utils.prepareContent
import com.example.domain.model.search.Anime
import com.example.domain.model.search.AnimeSort
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

        composeTestRule.setContent(prepareContent(animes = animes))
        composeTestRule.onNodeWithText("Demon Slayer").assertExists()
        composeTestRule.onNodeWithText("One Piece").assertExists()
    }

    @Test
    fun testOnTypeChanged() {

        var selectedType: AnimeType? = null
        val onTypeChanged: (AnimeType) -> Unit = {
            selectedType = it
        }

        composeTestRule.setContent(prepareContent(onTypeChanged = onTypeChanged))

        composeTestRule.onNodeWithTag("typeDropdown")
            .performClick()

        composeTestRule
            .onNode(
                hasText(AnimeType.MANGA.toString()).and(
                    hasAnyAncestor(
                        keyIsDefined(
                            SemanticsProperties.IsPopup
                        )
                    )
                )
            )
            .performClick()

        assertEquals(AnimeType.MANGA, selectedType)
    }

    @Test
    fun testOnSortChanged() {
        var selectedSort: AnimeSort? = null
        val onSortChanged: (AnimeSort) -> Unit = {
            selectedSort = it
        }

        composeTestRule.setContent(prepareContent(onSortChanged = onSortChanged))

        composeTestRule.onNodeWithTag("sortDropdown")
            .performClick()

        AnimeSortUtils.sortDisplayNames[AnimeSort.POPULARITY_DESC]?.let { sortText ->
            composeTestRule
                .onNode(
                    hasText(sortText).and(
                        hasAnyAncestor(
                            keyIsDefined(
                                SemanticsProperties.IsPopup
                            )
                        )
                    )
                )
                .performClick()
        } ?: throw IllegalArgumentException("Expected sort option not found in display names")

        assertEquals(AnimeSort.POPULARITY_DESC, selectedSort)
    }

    @Test
    fun testOnSearchChanged(){
        var searQuery: String? = null
        val onSearchChanged: (String) -> Unit ={
            searQuery = it
        }

        composeTestRule.setContent(prepareContent(onSearchChanged = onSearchChanged))
        composeTestRule
            .onNodeWithTag("searchBarTextField")
            .performTextInput("Naruto")
        assertEquals("Naruto", searQuery)
    }

    @Test
    fun testOnAnimeSelected() {
        var selectedAnime: Anime? = null
        val onAnimeSelected: (Anime) -> Unit = {
            selectedAnime = it
        }

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

        composeTestRule.setContent(
            prepareContent(
                animes = animes,
                onAnimeSelected = onAnimeSelected
            )
        )

        composeTestRule.onNode(hasText("Demon Slayer")).performClick()

        assertEquals("Demon Slayer", selectedAnime?.title)
    }

    @Test
    fun testOnToggleFavorite() {
        var toggledAnime: Anime? = null
        val onToggleFavorite: (Anime) -> Unit = {
            toggledAnime = it
        }

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

        composeTestRule.setContent(
            prepareContent(
                animes = animes,
                onToggleFavorite = onToggleFavorite
            )
        )

        composeTestRule.onNodeWithTag("FavoriteButton_1").performClick()

        assertEquals("Demon Slayer", toggledAnime?.title)
    }

    @Test
    fun testFavoriteAnimeStatus() {
        val favoriteAnime: Set<Int> = setOf(1)

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

        composeTestRule.setContent(
            prepareContent(
                animes = animes,
                favoriteAnime = favoriteAnime
            )
        )

        composeTestRule
            .onNodeWithTag("FavoriteButton_1")
            .assertIsDisplayed()
            .assert(hasContentDescription("Add to Favorites"))

        composeTestRule
            .onNodeWithTag("FavoriteButton_2")
            .assertIsDisplayed()
            .assert(hasContentDescription("Add to Favorites"))
    }
}




