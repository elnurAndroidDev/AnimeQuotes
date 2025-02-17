package com.isayevapps.domain.repository

sealed class LoadType {
    data object Refresh : LoadType()
    data object Append : LoadType()
}