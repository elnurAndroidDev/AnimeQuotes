package com.isayevapps.domain

data class AnimeItem(
    val animeId: Int,
    val title: String,
    val imgUrl: String,
    val synopsis: String,
    val score: Double,
    val episodes: Int,
    val type: String,
    val genres: List<String>,
    val airedFrom: String,
    val airedTo: String? = null
)
