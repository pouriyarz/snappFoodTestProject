package com.rezaie.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCharactersResponse(
    @SerialName("next") val next: String?,
    @SerialName("results") val results: List<CharactersResponse>?
)
