package com.isaevapps.data

import android.content.Context
import com.isaevapps.data.cloud.AnimeCloudRepositoryImpl
import com.isaevapps.data.cloud.AnimeService
import com.isaevapps.data.local.AnimeDataBase
import com.isaevapps.data.local.AnimeLocalRepositoryImp
import com.isayevapps.domain.cloud.AnimeCloudRepository
import com.isayevapps.domain.local.AnimeLocalRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val animeCloudRepository: AnimeCloudRepository
    val animeLocalRepository: AnimeLocalRepository
}

class DefaultAppContainer(context: Context) : AppContainer {
    private val baseUrl = "https://api.jikan.moe/v4/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: AnimeService by lazy {
        retrofit.create(AnimeService::class.java)
    }

    override val animeCloudRepository: AnimeCloudRepository by lazy {
        AnimeCloudRepositoryImpl(retrofitService)
    }
    override val animeLocalRepository: AnimeLocalRepository by lazy {
        AnimeLocalRepositoryImp(AnimeDataBase.getDatabase(context).animeDao())
    }
}