package com.isayevapps.animequotes.di

import com.isaevapps.data.cloud.AnimeCloudDataSourceImpl
import com.isaevapps.data.local.AnimeLocalDataSourceImpl
import com.isaevapps.data.repository.RepositoryImpl
import com.isayevapps.domain.cloud.AnimeCloudDataSource
import com.isayevapps.domain.local.AnimeLocalDataSource
import com.isayevapps.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAnimeCloudDataSource(animeCloudDataSourceImpl: AnimeCloudDataSourceImpl): AnimeCloudDataSource

    @Binds
    abstract fun bindAnimeLocalDataSource(animeLocalDataSourceImpl: AnimeLocalDataSourceImpl): AnimeLocalDataSource

    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository


}