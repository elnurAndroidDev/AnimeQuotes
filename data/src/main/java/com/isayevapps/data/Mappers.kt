package com.isayevapps.data

import com.isayevapps.domain.cloud.AnimeItem

fun AnimeInfo.toAnimeItem(): AnimeItem {
    return AnimeItem(
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