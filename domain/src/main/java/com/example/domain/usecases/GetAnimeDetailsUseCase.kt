package com.example.domain.usecases

import com.example.domain.model.detail.AnimeDetails
import com.example.domain.repositories.AnimeRepository
import javax.inject.Inject

class GetAnimeDetailsUseCase @Inject constructor(private val animeRepository: AnimeRepository) {
    suspend operator fun invoke(id: Int): AnimeDetails {
        return animeRepository.getAnimeDetails(id)
    }
}
