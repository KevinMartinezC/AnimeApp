package com.example.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient

object ApiService {
    private val okHttpClient = OkHttpClient.Builder().build()

    val apolloClient: ApolloClient = ApolloClient.Builder()
        .serverUrl("https://graphql.anilist.co")
        .okHttpClient(okHttpClient)
        .build()
}