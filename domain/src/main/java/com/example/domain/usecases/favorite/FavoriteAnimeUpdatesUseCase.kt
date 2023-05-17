package com.example.domain.usecases.favorite

import com.example.domain.repositories.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteAnimeUpdatesUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {

    operator fun invoke(): Flow<Set<Int>> {
        return favoriteRepository.favoriteAnime.map { list ->
            list.map { it.id }.toSet()
        }
    }
}