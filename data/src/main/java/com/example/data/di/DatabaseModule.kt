package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.FavoriteAnimeDao
import com.example.data.local.FavoriteDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFavoriteDatabase(@ApplicationContext context: Context): FavoriteDataBase {
        return Room.databaseBuilder(
            context,
            FavoriteDataBase::class.java,
            "favorite_anime_database"
        ).build()
    }

    @Provides
    fun provideFavoriteAnimeDao(database: FavoriteDataBase): FavoriteAnimeDao {
        return database.favoriteAnimeDao()
    }
}
