package com.rezaie.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("error") val error: ErrorMessageResponse?
)

@Serializable
data class ErrorMessageResponse(
    @SerialName("message") val message: String?
)