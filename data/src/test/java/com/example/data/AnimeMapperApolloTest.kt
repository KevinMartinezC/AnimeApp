package com.example.data

import com.example.data.mapper.apollo.toAnime
import com.example.data.mapper.apollo.toGraphQLMediaSort
import com.example.data.mapper.apollo.toGraphQLMediaType
import com.example.data.type.MediaSort
import com.example.data.type.MediaType
import com.example.domain.model.search.AnimeSort
import com.example.domain.model.search.AnimeType
import org.junit.Assert.assertEquals
import org.junit.Test

class AnimeMapperApolloTest {
    @Test
    fun `verify toAnime maps correctly`() {

        val medium = GetAnimeListQuery.Medium(
            id = 1,
            title = GetAnimeListQuery.Title(
                romaji = "Test Anime",
                english = null,
                native = null
            ),
            coverImage = GetAnimeListQuery.CoverImage(
                large = "Test URL"
            )
        )

        val anime = medium.toAnime()

        assertEquals(medium.id, anime.id)
        assertEquals(medium.title?.romaji.orEmpty(), anime.title)
        assertEquals(medium.coverImage?.large.orEmpty(), anime.imageUrl)
    }

    @Test
    fun `verify toGraphQLMediaSort maps AnimeSort correctly`() {
        // Convert and verify POPULARITY_DESC
        var mediaSort = AnimeSort.POPULARITY_DESC.toGraphQLMediaSort()
        assertEquals(MediaSort.POPULARITY_DESC, mediaSort)

        // Convert and verify EPISODES_DESC
        mediaSort = AnimeSort.EPISODES_DESC.toGraphQLMediaSort()
        assertEquals(MediaSort.EPISODES_DESC, mediaSort)

        // Convert and verify START_DATE
        mediaSort = AnimeSort.START_DATE.toGraphQLMediaSort()
        assertEquals(MediaSort.START_DATE, mediaSort)
    }

    @Test
    fun `verify toGraphQLMediaType maps  correctly`() {
        var mediaType = AnimeType.ANIME.toGraphQLMediaType()

        assertEquals(MediaType.ANIME, mediaType)

        mediaType = AnimeType.MANGA.toGraphQLMediaType()

        assertEquals(MediaType.MANGA, mediaType)
    }
}