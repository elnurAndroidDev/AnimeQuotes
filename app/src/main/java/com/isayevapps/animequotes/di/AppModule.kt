package com.isayevapps.animequotes.di

import android.content.Context
import androidx.room.Room
import com.isaevapps.data.cloud.AnimeService
import com.isaevapps.data.local.AnimeDao
import com.isaevapps.data.local.AnimeDataBase
import com.isayevapps.domain.cloud.AnimeCloudRepository
import com.isayevapps.domain.cloud.usecases.GetAnimeTitlesUseCase
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
object AppModule {

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
    fun provideGetAnimeTitlesUseCase(repository: AnimeCloudRepository): GetAnimeTitlesUseCase {
        return GetAnimeTitlesUseCase(repository)
    }

}