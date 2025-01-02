package com.rezaie.domain.domainCharacteres.entity

data class PlanetEntity(
    private val id: Int?,
    private val population: String?
) {
    fun getId() = id ?: 0
    fun getPopulation() = population ?: ""
}
