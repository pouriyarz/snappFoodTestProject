package com.rezaie.domain.domainCharacteres.entity

data class PlanetEntity(
    private var characterId: String,
    private val id: Int,
    private val url: String,
    private val population: String
) {
    fun getCharacterId() = characterId
    fun getId() = id
    fun getUrl() = url
    fun getPopulation() = population
}
