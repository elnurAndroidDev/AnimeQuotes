package com.isaevapps.data.cloud

import retrofit2.http.GET

interface AnimeService {
    @GET("top/anime")
    suspend fun getAnime(): AnimeResponse
}