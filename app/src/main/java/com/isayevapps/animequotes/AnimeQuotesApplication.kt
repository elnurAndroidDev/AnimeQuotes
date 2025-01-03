package com.isayevapps.animequotes

import android.app.Application
import com.isayevapps.data.AppContainer
import com.isayevapps.data.DefaultAppContainer
import com.isayevapps.presentation.screens.animetitles.AnimeTitlesViewModel
import com.isayevapps.presentation.screens.animetitles.ProvideViewModel

class AnimeQuotesApplication : Application(), ProvideViewModel {
    lateinit var appContainer: AppContainer
    lateinit var viewModel: AnimeTitlesViewModel
    override fun onCreate() {
        super.onCreate()
        appContainer = DefaultAppContainer()
        viewModel = AnimeTitlesViewModel(appContainer.animeRepository)
    }

    override fun provideViewModel(): AnimeTitlesViewModel {
        return viewModel
    }
}