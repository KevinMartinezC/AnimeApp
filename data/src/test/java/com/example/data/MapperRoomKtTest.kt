package com.example.data

import com.example.data.local.FavoriteAnimeEntity
import com.example.data.mapper.room.toFavoriteAnime
import com.example.data.mapper.room.toFavoriteAnimeEntity
import com.example.domain.model.favorite.FavoriteAnime
import org.junit.Assert.assertEquals
import org.junit.Test

class MapperRoomKtTest {
    @Test
    fun `verify toFavoriteAnime maps correctly`() {
        // Create a FavoriteAnimeEntity
        val favoriteAnimeEntity = FavoriteAnimeEntity(
            id = 1,
            title = "Test Anime",
            coverImage = "Test URL"
        )

        // Convert to FavoriteAnime
        val favoriteAnime = favoriteAnimeEntity.toFavoriteAnime()

        // Verify fields are the same
        assertEquals(favoriteAnimeEntity.id, favoriteAnime.id)
        assertEquals(favoriteAnimeEntity.title, favoriteAnime.title)
        assertEquals(favoriteAnimeEntity.coverImage, favoriteAnime.imageUrl)
    }

    @Test
    fun `verify toFavoriteAnimeEntity maps correctly`() {

        val favoriteAnime = FavoriteAnime(
            id = 1,
            title = "Test Anime",
            imageUrl = "Test URL"
        )

        // Convert to FavoriteAnimeEntity
        val favoriteAnimeEntity = favoriteAnime.toFavoriteAnimeEntity()

        // Verify fields are the same
        assertEquals(favoriteAnime.id, favoriteAnimeEntity.id)
        assertEquals(favoriteAnime.title, favoriteAnimeEntity.title)
        assertEquals(favoriteAnime.imageUrl, favoriteAnimeEntity.coverImage)
    }
}