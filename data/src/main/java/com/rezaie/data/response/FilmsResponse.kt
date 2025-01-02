package com.rezaie.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmsResponse(
    @SerialName("id") val id: Int?,
    @SerialName("title") val name: String?,
    @SerialName("opening_crawl") val openingCrawl: String?,
)
