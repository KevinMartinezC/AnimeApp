package com.example.data.repositoryimpl

import com.apollographql.apollo3.ApolloClient
import com.example.data.CharacterInfoQuery
import com.example.data.GetAnimeDetailsQuery
import com.example.data.GetAnimeListQuery
import com.example.data.extentions.executeListQuery
import com.example.data.extentions.executeQuery
import com.example.data.extentions.toOptional
import com.example.data.mapper.apollo.toAnime
import com.example.data.mapper.apollo.toAnimeDetails
import com.example.data.mapper.apollo.toCharacterDetails
import com.example.data.mapper.apollo.toGraphQLMediaSort
import com.example.data.mapper.apollo.toGraphQLMediaType
import com.example.domain.model.search.Anime
import com.example.domain.model.detail.AnimeDetails
import com.example.domain.model.search.AnimeSort
import com.example.domain.model.search.AnimeType
import com.example.domain.model.character.CharacterInfo
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
        if (search.isNullOrEmpty()) {
            return emptyList()
        }
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