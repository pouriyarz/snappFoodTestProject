package com.rezaie.domain.domainCharacteres.entity

data class SpeciesEntity(
    private var characterId: String,
    private val id: Int,
    private val language: String,
    private val name: String,
) {
    fun getCharacterId() = characterId
    fun getId() = id
    fun getName() = name
    fun getLanguage() = language
}
