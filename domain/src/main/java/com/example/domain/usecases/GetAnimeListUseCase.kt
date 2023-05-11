package com.example.domain.usecases

import com.example.domain.Anime
import com.example.domain.repositories.AnimeRepository
import javax.inject.Inject

class GetAnimeListUseCase @Inject constructor(private val animeRepository: AnimeRepository) {
    suspend operator fun invoke(page: Int, perPage: Int): List<Anime> {
        return animeRepository.getAnimeList(page, perPage)
    }
}