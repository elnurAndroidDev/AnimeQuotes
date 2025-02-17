package com.isayevapps.domain.repository

sealed class LoadResult {
    data object Success : LoadResult()
    data class Error(val error: Throwable) : LoadResult()
}

