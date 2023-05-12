package com.example.domain.usecases

import com.example.domain.Anime
import com.example.domain.AnimeSort
import com.example.domain.AnimeType
import com.example.domain.repositories.AnimeRepository
import javax.inject.Inject

class GetAnimeListUseCase @Inject constructor(private val animeRepository: AnimeRepository) {
    suspend operator fun invoke(
        page: Int,
        perPage: Int,
        type: AnimeType,
        sort: List<AnimeSort>,
    ): List<Anime> {
        return animeRepository.getAnimeList(page, perPage,type, sort )
    }
}