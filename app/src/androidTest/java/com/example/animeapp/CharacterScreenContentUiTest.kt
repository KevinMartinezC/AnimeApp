package com.example.animeapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.animeapp.components.character.CharacterScreenContent
import com.example.domain.model.character.CharacterInfo
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharacterScreenContentUiTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testCharacterScreenContent() {
        val characterInfo = CharacterInfo(
            id = 1,
            name = "Test Character",
            description = "Test Character Description",
            imageUrl = "https://i.blogs.es/bc1dd2/naruto/840_560.png",
        )

        composeTestRule.setContent {
            CharacterScreenContent(characterDetails = characterInfo)
        }

        composeTestRule.onNodeWithTag("CharacterScreenContent").assertIsDisplayed()
    }
}