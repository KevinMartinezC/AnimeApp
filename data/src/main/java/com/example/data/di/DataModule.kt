package com.example.data.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.data.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providesApolloClient(): ApolloClient {
        val okHttpClient = OkHttpClient.Builder().build()

        return ApolloClient.Builder()
            .serverUrl(BuildConfig.ANILIST_API__URL)
            .okHttpClient(okHttpClient)
            .build()
    }
}