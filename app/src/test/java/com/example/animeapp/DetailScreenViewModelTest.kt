package com.example.animeapp

import com.example.animeapp.components.detail.viewmodel.DetailScreenViewModel
import com.example.domain.model.detail.AnimeDetails
import com.example.domain.usecases.GetAnimeDetailsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DetailScreenViewModelTest {

    private val getAnimeDetailsUseCase: GetAnimeDetailsUseCase = mockk()

    private lateinit var viewModel: DetailScreenViewModel

    private val animeDetails = AnimeDetails(
        id = 1,
        title = "Test Anime",
        englishName = "Test Anime",
        japaneseName = "テストアニメ",
        imageUrl = "imageUrl",
        description = "Test description",
        episodes = 12,
        averageScore = 80,
        genres = listOf("Genre1", "Genre2"),
        characters = emptyList()
    )

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        coEvery { getAnimeDetailsUseCase(animeDetails.id) } returns animeDetails

        viewModel = DetailScreenViewModel(getAnimeDetailsUseCase)
    }

    @Test
    fun `fetchAnimeDetails updates _animeDetails`() = runTest {
        viewModel.fetchAnimeDetails(animeDetails.id)

        assertEquals(animeDetails, viewModel.animeDetails.value)
    }
}
