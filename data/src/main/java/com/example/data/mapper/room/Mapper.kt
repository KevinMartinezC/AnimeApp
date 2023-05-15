package com.example.data.mapper.room

import com.example.data.local.FavoriteAnimeEntity
import com.example.domain.model.favorite.FavoriteAnime


fun FavoriteAnimeEntity.toFavoriteAnime(): FavoriteAnime {
    return FavoriteAnime(
        id = id,
        title = title,
        imageUrl = coverImage
    )
}

fun FavoriteAnime.toFavoriteAnimeEntity(): FavoriteAnimeEntity {
    return FavoriteAnimeEntity(
        id = id,
        title = title,
        coverImage = imageUrl
    )
}
