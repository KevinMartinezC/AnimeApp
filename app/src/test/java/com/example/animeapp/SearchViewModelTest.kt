package com.example.animeapp

import com.example.animeapp.components.search.viewmodel.SearchViewModel
import com.example.domain.model.search.Anime
import com.example.domain.model.search.AnimeSort
import com.example.domain.model.search.AnimeType
import com.example.domain.usecases.GetAnimeListUseCase
import com.example.domain.usecases.favorite.AddFavoriteAnimeUseCase
import com.example.domain.usecases.favorite.FavoriteAnimeUpdatesUseCase
import com.example.domain.usecases.favorite.RemoveFavoriteAnimeUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class SearchViewModelTest {
    private val getAnimeListUseCase: GetAnimeListUseCase = mockk()
    private val addFavoriteAnimeUseCase: AddFavoriteAnimeUseCase = mockk(relaxed = true)
    private val removeFavoriteAnimeUseCase: RemoveFavoriteAnimeUseCase = mockk(relaxed = true)
    private val favoriteAnimeUpdatesUseCase: FavoriteAnimeUpdatesUseCase = mockk()
    private lateinit var viewModel: SearchViewModel
    private val anime = Anime(id = 1, title = "Test Anime", imageUrl = "imageUrl")

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        coEvery { favoriteAnimeUpdatesUseCase() } returns flowOf(emptySet())
        viewModel = SearchViewModel(
            getAnimeListUseCase,
            addFavoriteAnimeUseCase,
            removeFavoriteAnimeUseCase,
            favoriteAnimeUpdatesUseCase
        )
    }

    @Test
    fun `addToFavorites adds anime when it is not in favorites`() = runTest {
        viewModel.addToFavorites(anime)

        coVerify {
            addFavoriteAnimeUseCase.invoke(anime)
        }
    }

    @Test
    fun `addToFavorites removes anime when it is in favorites`() = runTest {
        coEvery { favoriteAnimeUpdatesUseCase() } returns flowOf(setOf(anime.id))

        viewModel = SearchViewModel(
            getAnimeListUseCase,
            addFavoriteAnimeUseCase,
            removeFavoriteAnimeUseCase,
            favoriteAnimeUpdatesUseCase
        )

        viewModel.addToFavorites(anime)

        coVerify {
            removeFavoriteAnimeUseCase.invoke(anime.id)
        }
    }

    @Test
    fun `onTypeChanged updates type`() = runTest {
        val newType = AnimeType.MANGA

        viewModel.onTypeChanged(newType)

        assertEquals(newType, viewModel.type.value)
    }

    @Test
    fun `onSortChanged updates sort`() = runTest {
        val newSort = AnimeSort.EPISODES_DESC

        viewModel.onSortChanged(newSort)

        assertEquals(listOf(newSort), viewModel.sort.value)
    }
}
