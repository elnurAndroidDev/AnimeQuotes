package com.isayevapps.data.cloud

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