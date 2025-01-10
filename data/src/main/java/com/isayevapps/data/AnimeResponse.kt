package com.isayevapps.data

import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @SerializedName("data")
    val data: List<AnimeInfo>,
    @SerializedName("pagination")
    val pagination: Pagination,
)

data class Pagination(
    @SerializedName("has_next_page")
    val hasNextPage: Boolean,
    @SerializedName("current_page")
    val currentPage: Int
)

data class AnimeInfo(
    @SerializedName("title")
    val title: String,
    @SerializedName("title_english")
    val titleEng: String?,
    val images: Images,
    val type: String,
    val episodes: Int,
    val score: Double,
    val synopsis: String,
    val genres: List<Genre>,
    val aired: Aired
)

data class Images(
    val jpg: Jpg
)

data class Jpg(
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("large_image_url")
    val largeImageUrl: String
)

data class Genre(
    val name: String
)

data class Aired(
    val from: String,
    val to: String?
)