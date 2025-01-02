package com.rezaie.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlanetResponse(
    @SerialName("id") val id: Int?,
    @SerialName("population") val population: String?,
)
