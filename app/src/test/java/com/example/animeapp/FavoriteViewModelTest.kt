package com.example.animeapp

import com.example.animeapp.components.favorite.viewmodel.FavoriteViewModel
import com.example.domain.model.favorite.FavoriteAnime
import com.example.domain.usecases.favorite.FavoriteAnimeUpdatesUseCase
import com.example.domain.usecases.favorite.GetFavoriteAnimeUseCase
import com.example.domain.usecases.favorite.RemoveFavoriteAnimeUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FavoriteViewModelTest {
    private val getFavoriteAnimeUseCase: GetFavoriteAnimeUseCase = mockk()
    private val removeFavoriteAnimeUseCase: RemoveFavoriteAnimeUseCase = mockk(relaxed = true)
    private val favoriteAnimeUpdatesUseCase: FavoriteAnimeUpdatesUseCase = mockk()
    private lateinit var viewModel: FavoriteViewModel

    private val favoriteAnime = FavoriteAnime(id = 1, title = "Test Anime", imageUrl = "imageUrl")

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        coEvery { getFavoriteAnimeUseCase() } returns flowOf(listOf(favoriteAnime))
        coEvery { favoriteAnimeUpdatesUseCase() } returns flowOf(setOf(favoriteAnime.id))
        viewModel = FavoriteViewModel(
            getFavoriteAnimeUseCase,
            removeFavoriteAnimeUseCase,
            favoriteAnimeUpdatesUseCase
        )
    }

    @Test
    fun `initializeData fetches favorite anime and updates uiState`() = runTest {

        val expectedFavoriteAnimeList = listOf(favoriteAnime)

        delay(500)

        assertEquals(
            expectedFavoriteAnimeList,
            viewModel.uiStateFavorite.value.favoriteAnimesList.value
        )
    }

    @Test
    fun `removeFromFavorites calls when anime is in favorites`() = runTest {
        viewModel.removeFromFavorites(favoriteAnime.id)

        coVerify {
            removeFavoriteAnimeUseCase.invoke(favoriteAnime.id)
        }
    }

    @Test
    fun `removeFromFavorites does not call when anime is not in favorites`() = runTest {
        coEvery { getFavoriteAnimeUseCase() } returns flowOf(emptyList())
        coEvery { favoriteAnimeUpdatesUseCase() } returns flowOf(emptySet())

        viewModel = FavoriteViewModel(
            getFavoriteAnimeUseCase,
            removeFavoriteAnimeUseCase,
            favoriteAnimeUpdatesUseCase
        )

        viewModel.removeFromFavorites(favoriteAnime.id)

        coVerify(exactly = 0) {
            removeFavoriteAnimeUseCase.invoke(favoriteAnime.id)
        }
    }
}
