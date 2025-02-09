package com.isaevapps.data.cloud

import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeService {
    @GET("top/anime")
    suspend fun getAnime(
        @Query("page") page: Int,
        @Query("limit") pageSize: Int
    ): AnimeResponse
}