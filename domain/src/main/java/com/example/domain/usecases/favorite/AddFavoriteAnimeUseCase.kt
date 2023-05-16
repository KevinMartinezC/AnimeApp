package com.example.domain.usecases.favorite

import com.example.domain.model.favorite.FavoriteAnime
import com.example.domain.model.search.Anime
import com.example.domain.repositories.FavoriteRepository
import javax.inject.Inject

class AddFavoriteAnimeUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    suspend operator fun invoke(anime: Anime) {
        val favoriteAnime = FavoriteAnime(
            id = anime.id,
            title = anime.title,
            imageUrl = anime.imageUrl
        )
        favoriteRepository.addFavoriteAnime(favoriteAnime)
    }
}