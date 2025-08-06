package com.isayevapps.presentation.utils

import com.isayevapps.domain.result.CloudError
import com.isayevapps.presentation.R

fun CloudError.toUiText(): UiText {
    return when (this) {
        CloudError.NO_INTERNET -> UiText.StringResource(R.string.no_internet)
        CloudError.SERVER_ERROR -> UiText.StringResource(R.string.server_error)
        CloudError.TIMEOUT -> UiText.StringResource(R.string.timeout)
        CloudError.UNKNOWN -> UiText.StringResource(R.string.unknown_error)
    }
}