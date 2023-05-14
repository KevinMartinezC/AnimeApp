package com.example.data.mapper

import com.example.data.CharacterInfoQuery
import com.example.domain.model.CharacterInfo

fun CharacterInfoQuery.Character.toCharacterDetails(): CharacterInfo {
    return CharacterInfo(
        id = id,
        name = name?.full.orEmpty(),
        imageUrl = image?.large.orEmpty(),
        description = description
    )
}