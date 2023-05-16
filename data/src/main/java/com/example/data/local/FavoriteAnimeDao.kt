package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteAnimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteAnime(favoriteAnimeEntity: FavoriteAnimeEntity)

    @Query("DELETE FROM favorite_animes WHERE id = :animeId")
    suspend fun removeFavoriteAnime(animeId: Int)

    @Query("SELECT * FROM favorite_animes")
    fun getFavoriteAnime(): Flow<List<FavoriteAnimeEntity>>
}