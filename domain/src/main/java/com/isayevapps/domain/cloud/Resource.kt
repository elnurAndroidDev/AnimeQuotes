package com.isayevapps.domain.cloud

sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val error: Throwable) : Resource<T>()
}