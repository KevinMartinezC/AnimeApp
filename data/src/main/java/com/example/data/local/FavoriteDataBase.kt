package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [FavoriteAnimeEntity::class], version = 1, exportSchema = false)

abstract class FavoriteDataBase : RoomDatabase() {
    abstract fun favoriteAnimeDao(): FavoriteAnimeDao

}
