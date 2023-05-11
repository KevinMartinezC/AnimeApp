package com.example.animeapp.di

import com.example.data.AnimeRepositoryImpl
import com.example.domain.AnimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAnimeRepository(animeRepositoryImpl: AnimeRepositoryImpl): AnimeRepository {
        return animeRepositoryImpl
    }


}