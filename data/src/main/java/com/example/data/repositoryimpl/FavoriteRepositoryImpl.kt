package com.example.data.repositoryimpl

import com.example.data.local.FavoriteAnimeDao
import com.example.data.local.FavoriteAnimeEntity
import com.example.data.local.toFavoriteAnime
import com.example.data.local.toFavoriteAnimeEntity
import com.example.domain.FavoriteAnime
import com.example.domain.repositories.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val favoriteAnimeDao: FavoriteAnimeDao
) : FavoriteRepository {

    override val favoriteAnime: Flow<List<FavoriteAnime>>
        get() = favoriteAnimeDao.getFavoriteAnime()
            .map { list -> list.map(FavoriteAnimeEntity::toFavoriteAnime) }

    override suspend fun addFavoriteAnime(favoriteAnime: FavoriteAnime) {
        favoriteAnimeDao.addFavoriteAnime(favoriteAnime.toFavoriteAnimeEntity())
    }

    override suspend fun removeFavoriteAnime(animeId: Int) {
        favoriteAnimeDao.removeFavoriteAnime(animeId)
    }
}
