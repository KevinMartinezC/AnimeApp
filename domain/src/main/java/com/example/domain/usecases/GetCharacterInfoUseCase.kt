package com.example.domain.usecases

import com.example.domain.model.character.CharacterInfo
import com.example.domain.repositories.AnimeRepository
import javax.inject.Inject

class GetCharacterInfoUseCase @Inject constructor(private val animeRepository: AnimeRepository) {
    suspend operator fun invoke(id: Int): CharacterInfo {
        return animeRepository.getCharacter(id)
    }
}