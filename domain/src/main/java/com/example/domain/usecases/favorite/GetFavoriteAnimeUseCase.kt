package com.example.domain.usecases.favorite

import com.example.domain.model.favorite.FavoriteAnime
import com.example.domain.repositories.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteAnimeUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    operator fun invoke(): Flow<List<FavoriteAnime>> {
        return favoriteRepository.favoriteAnime
    }
}