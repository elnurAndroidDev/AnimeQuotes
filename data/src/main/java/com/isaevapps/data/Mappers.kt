package com.isaevapps.data

import com.isaevapps.data.cloud.AnimeInfo
import com.isaevapps.data.local.entities.AnimeEntity
import com.isaevapps.data.local.entities.RemoteKeysEntity
import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.local.RemoteKeys

fun AnimeInfo.toDomain(): AnimeItem {
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

fun AnimeEntity.toDomain(): AnimeItem {
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

fun RemoteKeysEntity.toDomain(): RemoteKeys {
    return RemoteKeys(
        prevKey = this.prevKey,
        nextKey = this.nextKey
    )
}

fun RemoteKeys.toEntity(): RemoteKeysEntity {
    return RemoteKeysEntity(
        prevKey = this.prevKey,
        nextKey = this.nextKey
    )
}