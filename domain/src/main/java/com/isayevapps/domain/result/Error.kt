package com.isayevapps.domain.result

sealed interface Error

enum class CloudError: Error {
    NO_INTERNET,
    SERVER_ERROR,
    TIMEOUT,
    UNKNOWN
}