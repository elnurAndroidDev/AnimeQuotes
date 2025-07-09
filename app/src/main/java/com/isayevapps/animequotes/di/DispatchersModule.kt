package com.isayevapps.animequotes.di

import com.isayevapps.animequotes.network.AppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @Provides
    @Singleton
    fun provideAppDispatchers(): AppDispatchers {
        return AppDispatchers(
            io = kotlinx.coroutines.Dispatchers.IO,
            main = kotlinx.coroutines.Dispatchers.Main,
            default = kotlinx.coroutines.Dispatchers.Default
        )
    }
}