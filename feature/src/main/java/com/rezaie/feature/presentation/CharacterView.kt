package com.rezaie.feature.presentation

import kotlinx.serialization.Serializable

@Serializable
data class CharacterView(
    val id: Int,
    val name: String,
    val birthYear: String,
    val height: String,
    val species: List<String>?,
    val homeWorld: String,
    val url: String?,
    val films: List<String>?,
)