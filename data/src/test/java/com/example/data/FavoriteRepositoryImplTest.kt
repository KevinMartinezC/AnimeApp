package com.example.data

import com.example.data.local.FavoriteAnimeDao
import com.example.data.local.FavoriteAnimeEntity
import com.example.data.repositoryimpl.FavoriteRepositoryImpl
import com.example.domain.model.favorite.FavoriteAnime
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class FavoriteRepositoryImplTest{
    private lateinit var favoriteAnimeDao: FavoriteAnimeDao
    private lateinit var favoriteRepository: FavoriteRepositoryImpl

    @Before
    fun setup() {
        favoriteAnimeDao = mockk(relaxed = true)
        favoriteRepository = FavoriteRepositoryImpl(favoriteAnimeDao)
    }

    @Test
    fun `getFavoriteAnime returns mapped anime list`() = runTest {

        val expectedFavoriteAnime = listOf(
            FavoriteAnime(id = 1, imageUrl = "url", title = "title")
        )
        val favoriteAnimeEntity = FavoriteAnimeEntity(id = 1, title = "title", coverImage = "url", )

        coEvery { favoriteAnimeDao.getFavoriteAnime() } returns flow { emit(listOf(favoriteAnimeEntity)) }

        val result = favoriteRepository.favoriteAnime.toList().first()

        assertEquals(expectedFavoriteAnime, result)
    }

    @Test
    fun `addFavoriteAnime calls dao add method`() = runTest{

        val favoriteAnime = FavoriteAnime(id = 1, imageUrl = "url", title = "title")

        favoriteRepository.addFavoriteAnime(favoriteAnime)

        coVerify { favoriteAnimeDao.addFavoriteAnime(any()) }
    }

    @Test
    fun `removeFavoriteAnime calls dao remove method`() = runTest {
        val animeId = 123

        favoriteRepository.removeFavoriteAnime(animeId)

        coVerify { favoriteAnimeDao.removeFavoriteAnime(animeId) }
    }
}