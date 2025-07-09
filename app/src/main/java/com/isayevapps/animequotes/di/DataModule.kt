package com.isayevapps.animequotes.di

import android.content.Context
import com.isaevapps.data.cloud.AnimeService
import com.isaevapps.data.local.dao.AnimeDao
import com.isaevapps.data.local.AnimeDataBase
import com.isaevapps.data.local.dao.FavoriteDao
import com.isayevapps.animequotes.network.AppDispatchers
import com.isayevapps.domain.repository.FavoritesRepository
import com.isayevapps.domain.repository.Repository
import com.isayevapps.domain.repository.SearchRepository
import com.isayevapps.domain.usecase.AddToFavoritesUseCase
import com.isayevapps.domain.usecase.GetAllAnimeUseCase
import com.isayevapps.domain.usecase.GetAnimeDetailsUseCase
import com.isayevapps.domain.usecase.GetDetailsFromCloudUseCase
import com.isayevapps.domain.usecase.GetFavoriteDetailUseCase
import com.isayevapps.domain.usecase.GetFavoritesUseCase
import com.isayevapps.domain.usecase.GetSearchResultUseCase
import com.isayevapps.domain.usecase.IsFavoriteUseCase
import com.isayevapps.domain.usecase.LoadAnimeUseCase
import com.isayevapps.domain.usecase.RemoveFromFavoritesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
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
    fun provideFavoriteDao(database: AnimeDataBase): FavoriteDao {
        return database.favoriteDao()
    }

    @Provides
    @Singleton
    fun provideGetAllAnimeTitlesUseCase(repository: Repository): GetAllAnimeUseCase {
        return GetAllAnimeUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAnimeDetailsUseCase(repository: Repository): GetAnimeDetailsUseCase {
        return GetAnimeDetailsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideLoadAnimeUseCase(repository: Repository, dispatchers: AppDispatchers): LoadAnimeUseCase {
        return LoadAnimeUseCase(repository, dispatchers.io)
    }

    @Provides
    @Singleton
    fun provideAddToFavoriteUseCase(favoritesRepository: FavoritesRepository, dispatchers: AppDispatchers): AddToFavoritesUseCase {
        return AddToFavoritesUseCase(favoritesRepository, dispatchers.io)
    }

    @Provides
    @Singleton
    fun provideIsFavoriteUseCase(favoritesRepository: FavoritesRepository, dispatchers: AppDispatchers): IsFavoriteUseCase {
        return IsFavoriteUseCase(favoritesRepository, dispatchers.io)
    }

    @Provides
    @Singleton
    fun provideRemoveFromFavoritesUseCase(favoritesRepository: FavoritesRepository, dispatchers: AppDispatchers): RemoveFromFavoritesUseCase {
        return RemoveFromFavoritesUseCase(favoritesRepository, dispatchers.io)
    }

    @Provides
    @Singleton
    fun provideGetAllFavoritesUseCase(favoritesRepository: FavoritesRepository): GetFavoritesUseCase {
        return GetFavoritesUseCase(favoritesRepository)
    }

    @Provides
    @Singleton
    fun provideGetFavoriteDetailUseCase(favoritesRepository: FavoritesRepository, dispatchers: AppDispatchers): GetFavoriteDetailUseCase {
        return GetFavoriteDetailUseCase(favoritesRepository, dispatchers.io)
    }

    @Provides
    @Singleton
    fun provideGetSearchResultUseCase(searchRepository: SearchRepository, dispatchers: AppDispatchers): GetSearchResultUseCase {
        return GetSearchResultUseCase(searchRepository, dispatchers.io)
    }

    @Provides
    @Singleton
    fun provideGetDetailsFromCloudUseCase(repository: Repository, dispatchers: AppDispatchers): GetDetailsFromCloudUseCase {
        return GetDetailsFromCloudUseCase(repository, dispatchers.io)
    }

}