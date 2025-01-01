package com.rezaie.domain.domainCharacteres.entity

data class FilmsEntity(
    private var characterId: String,
    private var id: Int,
    private val name: String,
    private val description: String
) {
    fun getCharacterId() = characterId
    fun getId() = id
    fun getName() = name
    fun getDescription() = description
}
