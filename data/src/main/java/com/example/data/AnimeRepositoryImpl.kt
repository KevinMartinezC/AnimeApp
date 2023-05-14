package com.example.data

import com.apollographql.apollo3.ApolloClient
import com.example.data.extentions.executeListQuery
import com.example.data.extentions.executeQuery
import com.example.data.extentions.toOptional
import com.example.data.mapper.toAnime
import com.example.data.mapper.toAnimeDetails
import com.example.data.mapper.toCharacterDetails
import com.example.data.mapper.toGraphQLMediaSort
import com.example.data.mapper.toGraphQLMediaType
import com.example.domain.model.Anime
import com.example.domain.model.AnimeDetails
import com.example.domain.model.AnimeSort
import com.example.domain.model.AnimeType
import com.example.domain.model.CharacterInfo
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
        return apolloClient.executeListQuery(query) { data ->
            data.Page?.media?.mapNotNull { it?.toAnime() } ?: emptyList()
        }
    }


    override suspend fun getAnimeDetails(id: Int): AnimeDetails {
        val query = GetAnimeDetailsQuery(id)
        return apolloClient.executeQuery(query) { it.Media?.toAnimeDetails() }
    }

    override suspend fun getCharacter(id: Int): CharacterInfo {
        val query = CharacterInfoQuery(id.toOptional())
        return apolloClient.executeQuery(query) { it.Character?.toCharacterDetails() }
    }
}