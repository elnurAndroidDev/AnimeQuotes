package com.isayevapps.domain.cloud

data class AnimeItem(
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
