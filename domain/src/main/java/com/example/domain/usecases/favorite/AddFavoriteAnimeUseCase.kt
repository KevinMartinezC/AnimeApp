package com.example.domain.usecases.favorite

import com.example.domain.FavoriteAnime
import com.example.domain.repositories.FavoriteRepository
import javax.inject.Inject

class AddFavoriteAnimeUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    suspend operator fun invoke(favoriteAnime: FavoriteAnime) {
        favoriteRepository.addFavoriteAnime(favoriteAnime)
    }
}