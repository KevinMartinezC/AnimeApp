package com.example.data

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.example.data.extentions.toOptional
import com.example.domain.Anime
import com.example.domain.AnimeRepository
import java.util.OptionalInt

class AnimeRepositoryImpl(private val apolloClient: ApolloClient) : AnimeRepository {
    override suspend fun getAnimeList(page: Int, perPage: Int): List<Anime> {
        val query = GetAnimeListQuery(page.toOptional(), perPage.toOptional())

        val response = apolloClient.query(query).execute()
        if (response.hasErrors()) {
            Log.e("AnimeRepositoryImpl", "Error fetching anime list: ${response.errors}")
            return emptyList()
        }

        val mediumList = response.data?.Page?.media ?: emptyList<GetAnimeListQuery.Medium>()
        return mediumList.mapNotNull { it?.toAnime() }
    }
}

