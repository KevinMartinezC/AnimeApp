package com.example.data.local

import com.example.domain.FavoriteAnime


fun FavoriteAnimeEntity.toFavoriteAnime(): FavoriteAnime{
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
