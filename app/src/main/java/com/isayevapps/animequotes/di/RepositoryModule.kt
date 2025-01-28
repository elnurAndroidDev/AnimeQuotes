package com.isayevapps.animequotes.di

import com.isaevapps.data.cloud.AnimeCloudRepositoryImpl
import com.isayevapps.domain.cloud.AnimeCloudRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAnimeCloudRepository(animeCloudRepositoryImpl: AnimeCloudRepositoryImpl): AnimeCloudRepository

}