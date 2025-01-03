package com.rezaie.domain.domainCharacteres.entity

import com.rezaie.components.extenssions.orZero

data class PlanetEntity(
    private val id: Int?,
    private val population: String?
) {
    fun getId() = id.orZero()
    fun getPopulation() = population.orEmpty()
}
