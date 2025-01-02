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
    val title: String,
    val images: Images
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