package com.example.domain.usecases

import com.example.domain.model.Anime
import com.example.domain.model.AnimeSort
import com.example.domain.model.AnimeType
import com.example.domain.repositories.AnimeRepository
import javax.inject.Inject

class GetAnimeListUseCase @Inject constructor(private val animeRepository: AnimeRepository) {
    suspend operator fun invoke(
        page: Int,
        perPage: Int,
        type: AnimeType,
        sort: List<AnimeSort>,
        search: String? = null
    ): List<Anime> {
        return animeRepository.getAnimeList(page, perPage,type, sort , search)
    }
}