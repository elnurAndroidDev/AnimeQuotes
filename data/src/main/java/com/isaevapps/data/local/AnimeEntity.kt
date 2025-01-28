package com.isaevapps.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_titles")
data class AnimeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
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
