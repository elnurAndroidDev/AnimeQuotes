package com.isayevapps.animequotes.di

import android.content.Context
import com.isaevapps.data.cloud.AnimeService
import com.isaevapps.data.local.dao.AnimeDao
import com.isaevapps.data.local.AnimeDataBase
import com.isaevapps.data.local.dao.RemoteKeysDao
import com.isayevapps.domain.repository.Repository
import com.isayevapps.domain.usecase.GetAnimeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRetrofitService(): AnimeService {
        val baseUrl = "https://api.jikan.moe/v4/"
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(AnimeService::class.java)
    }

    @Provides
    @Singleton
    fun provideAnimeDatabase(@ApplicationContext context: Context): AnimeDataBase {
        return AnimeDataBase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideAnimeDao(database: AnimeDataBase): AnimeDao {
        return database.animeDao()
    }

    @Provides
    @Singleton
    fun provideRemoteKeysDao(database: AnimeDataBase): RemoteKeysDao {
        return database.remoteKeysDao()
    }

    @Provides
    @Singleton
    fun provideGetAnimeTitlesUseCase(repository: Repository): GetAnimeUseCase {
        return GetAnimeUseCase(repository)
    }

}