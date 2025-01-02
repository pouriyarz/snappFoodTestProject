package com.rezaie.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    @SerialName("id") val id: Int?,
    @SerialName("height") val height: String?,
    @SerialName("name") val name: String?,
    @SerialName("birth_year") val birthYear: String?,
    @SerialName("homeworld") val homeWorld: String?,
    @SerialName("url") val url: String?,
    @SerialName("films") val films: List<String>?,
    @SerialName("species") val species: List<String>?,
    val query: String?
)
