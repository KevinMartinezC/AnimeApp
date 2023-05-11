package com.example.domain

import javax.inject.Inject

class GetAnimeListUseCase @Inject constructor(private val animeRepository: AnimeRepository) {
    suspend operator fun invoke(page: Int, perPage: Int): List<Anime> {
        return animeRepository.getAnimeList(page, perPage)
    }
}