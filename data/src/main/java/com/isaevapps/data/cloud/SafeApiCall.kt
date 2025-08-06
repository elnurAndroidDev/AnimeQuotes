package com.isaevapps.data.cloud

import com.isayevapps.domain.result.CloudError
import com.isayevapps.domain.result.Result
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

suspend fun <T, R> safeApiCall(
    apiCall: suspend () -> T,
    map: (T) -> R
): Result<R, CloudError> {
    return try {
        val response = apiCall()
        Result.Success(map(response))
    } catch (e: IOException) {
        Result.Error(CloudError.NO_INTERNET)
    } catch (e: HttpException) {
        Result.Error(CloudError.SERVER_ERROR)
    } catch (e: SocketTimeoutException) {
        Result.Error(CloudError.TIMEOUT)
    } catch (e: Exception) {
        Result.Error(CloudError.UNKNOWN)
    }
}