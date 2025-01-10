package com.isayevapps.data

import com.isayevapps.domain.cloud.AnimeCloudRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val animeCloudRepository: AnimeCloudRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://api.jikan.moe/v4/"

    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    /**
     * Retrofit service object for creating api calls
     */
    private val retrofitService: AnimeService by lazy {
        retrofit.create(AnimeService::class.java)
    }

    /**
     * DI implementation for Mars photos repository
     */
    override val animeCloudRepository: AnimeCloudRepository by lazy {
        AnimeCloudRepositoryImpl(retrofitService)
    }
}