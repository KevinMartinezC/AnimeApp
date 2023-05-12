package com.example.data

import com.apollographql.apollo3.ApolloClient
import com.example.data.extentions.toOptional
import com.example.data.mapper.toAnime
import com.example.data.mapper.toGraphQLMediaSort
import com.example.data.mapper.toGraphQLMediaType
import com.example.domain.Anime
import com.example.domain.AnimeSort
import com.example.domain.AnimeType
import com.example.domain.repositories.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : AnimeRepository {
    override suspend fun getAnimeList(
        page: Int,
        perPage: Int,
        type: AnimeType,
        sort: List<AnimeSort>,
        search: String?

    ): List<Anime> {
        val query = GetAnimeListQuery(
            page.toOptional(),
            perPage.toOptional(),
            type.toGraphQLMediaType().toOptional(),
            sort.map { it.toGraphQLMediaSort() }.toOptional(),
            search.toOptional()
        )
        return try {
            val response = apolloClient.query(query).execute()
            if (response.hasErrors()) {
                return emptyList()
            }

            val mediumList = response.data?.Page?.media ?: emptyList<GetAnimeListQuery.Medium>()
            mediumList.mapNotNull { it?.toAnime() }
        } catch (e: Exception) {
            emptyList()
        }

    }
}

