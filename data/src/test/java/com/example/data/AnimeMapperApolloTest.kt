package com.example.data

import com.example.data.mapper.apollo.toAnime
import com.example.data.mapper.apollo.toGraphQLMediaSort
import com.example.data.mapper.apollo.toGraphQLMediaType
import com.example.data.type.MediaSort
import com.example.data.type.MediaType
import com.example.data.type.buildMedia
import com.example.data.type.buildMediaCoverImage
import com.example.data.type.buildMediaTitle
import com.example.data.type.buildPage
import com.example.domain.model.search.AnimeSort
import com.example.domain.model.search.AnimeType
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AnimeMapperApolloTest {


    @Test
    fun `verify toAnime maps correctly`() {

        val data = GetAnimeListQuery.Data {
            Page = buildPage {
                media = listOf(
                    buildMedia {
                        id = 1
                        title = buildMediaTitle {
                            romaji = "Test"
                            english = "Test"
                            native = "test"

                        }
                        coverImage = buildMediaCoverImage {
                            large = "Url"
                        }
                    }
                )
            }
        }


        data.Page?.media?.get(0)?.let { media ->

            val anime = media.toAnime()

            assertEquals(media.id, anime.id)
            assertEquals(media.title?.romaji.orEmpty(), anime.title)
            assertEquals(media.coverImage?.large.orEmpty(), anime.imageUrl)
        }
    }

    @Test
    fun `verify toGraphQLMediaSort maps AnimeSort correctly`() {
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
