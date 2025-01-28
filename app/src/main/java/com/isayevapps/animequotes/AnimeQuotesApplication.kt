package com.isayevapps.animequotes

import android.app.Application
import com.isaevapps.data.AppContainer
import com.isaevapps.data.DefaultAppContainer
import com.isayevapps.domain.cloud.usecases.GetAnimeTitlesUseCase

class AnimeQuotesApplication : Application(){
    lateinit var appContainer: AppContainer
    lateinit var getAnimeTitlesUseCase: GetAnimeTitlesUseCase
    override fun onCreate() {
        super.onCreate()
        appContainer = DefaultAppContainer(this)
        getAnimeTitlesUseCase = GetAnimeTitlesUseCase(appContainer.animeCloudRepository)
    }
}