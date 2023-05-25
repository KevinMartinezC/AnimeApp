package com.example.animeapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.animeapp.components.search.SearchBar
import com.example.animeapp.theme.MyApplicationTheme
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchBarUITest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun searchBarTest() {
        // Initialize test variables
        var searchQuery = ""
        val expectedQuery = "Naruto"

        composeTestRule.setContent {
            MyApplicationTheme {
                SearchBar(
                    searchQuery = searchQuery,
                    onSearchChanged = { searchQuery = it }
                )
            }
        }

        composeTestRule
            .onNodeWithText(text = "Search")
            .performTextInput(expectedQuery)

        // Assert the query has changed to expected query
        assertEquals(expectedQuery, searchQuery)
    }

    @Test
    fun clearButtonRemovesTextFromSearchBar() {
        var searchQuery = "Naruto"

        composeTestRule.setContent {
            MyApplicationTheme {
                SearchBar(
                    searchQuery = searchQuery,
                    onSearchChanged = { searchQuery = it }
                )
            }
        }


        // Click the clear button
        composeTestRule
            .onNodeWithTag("clearButton")
            .performClick()

        // Assert the query has been cleared
        assertEquals("", searchQuery)
    }
}