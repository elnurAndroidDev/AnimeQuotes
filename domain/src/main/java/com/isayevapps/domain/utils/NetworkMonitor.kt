package com.isayevapps.domain.utils

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    fun observe(): Flow<Boolean>
}