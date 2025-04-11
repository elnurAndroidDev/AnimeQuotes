package com.isayevapps.domain

data class AnimeItem(
    val animeId: Int,
    val title: String,
    val imgUrl: String,
    val synopsis: String? = null,
    val score: Float? = null,
    val episodes: Int? = null,
    val type: String? = null,
    val genres: List<String>,
    val airedFrom: String? = null,
    val airedTo: String? = null
)
