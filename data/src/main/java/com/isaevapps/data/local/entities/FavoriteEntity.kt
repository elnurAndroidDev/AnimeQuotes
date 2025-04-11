package com.isaevapps.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table", indices = [Index(value = ["animeId"], unique = true)])
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
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
