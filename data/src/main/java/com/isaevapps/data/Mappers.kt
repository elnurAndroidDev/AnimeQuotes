package com.isaevapps.data

import com.isaevapps.data.cloud.AnimeInfo
import com.isaevapps.data.local.AnimeEntity
import com.isayevapps.domain.AnimeItem

fun AnimeInfo.toAnimeItem(): AnimeItem {
    return AnimeItem(
        animeId = this.animeId,
        title = this.titleEng ?: this.title,
        imgUrl = this.images.jpg.imageUrl,
        type = type,
        synopsis = this.synopsis,
        episodes = this.episodes,
        score = this.score,
        airedFrom = this.aired.from,
        airedTo = this.aired.to,
        genres = this.genres.map { it.name }
    )
}

fun AnimeItem.toAnimeEntity(): AnimeEntity {
    return AnimeEntity(
        animeId = this.animeId,
        title = this.title,
        imgUrl = this.imgUrl,
        type = this.type,
        synopsis = this.synopsis,
        episodes = this.episodes,
        score = this.score,
        airedFrom = this.airedFrom,
        airedTo = this.airedTo,
        genres = this.genres
    )
}

fun AnimeEntity.toAnimeItem(): AnimeItem {
    return AnimeItem(
        animeId = this.animeId,
        title = this.title,
        imgUrl = this.imgUrl,
        type = this.type,
        synopsis = this.synopsis,
        episodes = this.episodes,
        score = this.score,
        airedFrom = this.airedFrom,
        airedTo = this.airedTo,
        genres = this.genres
    )
}