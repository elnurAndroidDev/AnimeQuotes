package com.isaevapps.data.utils

import com.isayevapps.domain.cloud.Resource


//suspend fun <T> safeApiCall(mapperToDomain: Mapper, apiCall: suspend () -> T): Resource<T> = try {
//    val response = apiCall.invoke()
//    Resource.Success(response.map(mapperToDomain))
//} catch (e: Exception) {
//    Resource.Error(e)
//}