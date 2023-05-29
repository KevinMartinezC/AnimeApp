package com.example.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.annotations.ApolloExperimental
import com.apollographql.apollo3.testing.QueueTestNetworkTransport
import com.apollographql.apollo3.testing.enqueueTestResponse
import com.example.data.extentions.toOptional
import com.example.data.mapper.apollo.toAnime
import com.example.data.mapper.apollo.toAnimeDetails
import com.example.data.mapper.apollo.toCharacterDetails
import com.example.data.mapper.apollo.toGraphQLMediaSort
import com.example.data.mapper.apollo.toGraphQLMediaType
import com.example.data.repositoryimpl.AnimeRepositoryImpl
import com.example.data.type.buildCharacter
import com.example.data.type.buildCharacterConnection
import com.example.data.type.buildCharacterImage
import com.example.data.type.buildCharacterName
import com.example.data.type.buildMedia
import com.example.data.type.buildMediaCoverImage
import com.example.data.type.buildMediaTitle
import com.example.data.type.buildPage
import com.example.domain.model.search.AnimeSort
import com.example.domain.model.search.AnimeType
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AnimeRepositoryImplTest {

    private lateinit var apolloClient: ApolloClient
    private lateinit var animeRepository: AnimeRepositoryImpl

    @OptIn(ApolloExperimental::class)
    @Before
    fun setup() {
        apolloClient = ApolloClient.Builder()
            .networkTransport(QueueTestNetworkTransport())
            .build()

        animeRepository = AnimeRepositoryImpl(apolloClient)
    }

    @OptIn(ApolloExperimental::class)
    @Test
    fun `getAnimeList returns correct anime list`() = runTest {
        // Given
        val query = GetAnimeListQuery(
            1.toOptional(),
            10.toOptional(),
            AnimeType.ANIME.toGraphQLMediaType().toOptional(),
            listOf(AnimeSort.POPULARITY_DESC).map { it.toGraphQLMediaSort() }.toOptional(),
            "search".toOptional()
        )

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

        apolloClient.enqueueTestResponse(query, data)

        val result = animeRepository.getAnimeList(
            1,
            10,
            AnimeType.ANIME,
            listOf(AnimeSort.POPULARITY_DESC),
            "search")

        val expectedAnimeList = data.Page?.media?.mapNotNull { it?.toAnime() } ?: emptyList()
        assertEquals(expectedAnimeList, result)
    }

    @OptIn(ApolloExperimental::class)
    @Test
    fun `getAnimeDetails returns correct AnimeDetails`() = runTest {
        var id = 1
        val query = GetAnimeDetailsQuery(id)

        val data = GetAnimeDetailsQuery.Data {
            Media = buildMedia {
                id = 1
                title = buildMediaTitle {
                    romaji = "Test"
                    english = "Test"
                    native = "Test"
                }
                coverImage = buildMediaCoverImage {
                    large = "Url"
                }
                description = "Test description"
                episodes = 12
                averageScore = 80
                genres = listOf("Genre1", "Genre2")
                characters = buildCharacterConnection {
                    nodes = listOf(
                        buildCharacter {
                            id = 1
                            name = buildCharacterName {
                                full = "Test Character"
                            }
                            image = buildCharacterImage {
                                large = "CharacterUrl"
                            }
                            description = "Test Character description"
                        }
                    )
                }
            }
        }

        apolloClient.enqueueTestResponse(query, data)

        val result = animeRepository.getAnimeDetails(id)

        val expectedAnimeDetails = data.Media?.toAnimeDetails()
        assertEquals(expectedAnimeDetails, result)
    }

    @OptIn(ApolloExperimental::class)
    @Test
    fun `getCharacter returns correct CharacterInfo`() = runTest {
        // Given
        var id = 1
        val query = CharacterInfoQuery(id.toOptional())

        val data = CharacterInfoQuery.Data {
            Character = buildCharacter {
                id = 1
                name = buildCharacterName {
                    full = "Test Character"
                }
                image = buildCharacterImage {
                    large = "CharacterUrl"
                }
                description = "Test Character description"
            }
        }

        apolloClient.enqueueTestResponse(query, data)

        // When
        val result = animeRepository.getCharacter(id)

        // Then
        val expectedCharacterInfo = data.Character?.toCharacterDetails()
        assertEquals(expectedCharacterInfo, result)
    }
}
