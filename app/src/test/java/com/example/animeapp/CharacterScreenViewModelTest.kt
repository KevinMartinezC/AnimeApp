package com.example.animeapp

import com.example.animeapp.components.character.viewmodel.CharacterScreenViewModel
import com.example.domain.model.character.CharacterInfo
import com.example.domain.usecases.GetCharacterInfoUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class CharacterScreenViewModelTest {

    private val getCharacterInfoUseCase: GetCharacterInfoUseCase = mockk()

    private lateinit var viewModel: CharacterScreenViewModel

    private val characterInfo = CharacterInfo(
        id = 1,
        name = "Test Character",
        imageUrl = "imageUrl",
        description = "Description"
    )

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        coEvery { getCharacterInfoUseCase(characterInfo.id) } returns characterInfo
        viewModel = CharacterScreenViewModel(getCharacterInfoUseCase)
    }

    @Test
    fun fetchCharacterDetails_updatesCharacterDetails() = runTest {
        viewModel.fetchCharacterDetails(characterInfo.id)

        assertEquals(characterInfo, viewModel.characterDetails.value)
    }
}
