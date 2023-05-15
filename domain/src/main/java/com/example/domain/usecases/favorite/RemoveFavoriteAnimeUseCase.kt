package com.example.domain.usecases.favorite

import com.example.domain.repositories.FavoriteRepository
import javax.inject.Inject

class RemoveFavoriteAnimeUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    suspend operator fun invoke(animeId: Int) {
        favoriteRepository.removeFavoriteAnime(animeId)
    }
}