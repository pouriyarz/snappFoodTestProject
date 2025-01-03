package com.rezaie.domain.domainCharacteres.entity

import com.rezaie.components.extenssions.orZero

data class CharacterDetailEntity(
    private val id: Int?,
    private val name: String?,
    private val birthYear: String?,
    private val height: String?,
    private val species: List<SpeciesEntity>?,
    private val homeWorld: PlanetEntity?,
    private val films: List<FilmsEntity>?,
    private val url: String?
) {
    fun getId() = id.orZero()
    fun getName() = name.orEmpty()
    fun getBirthYear() = birthYear.orEmpty()
    fun getHeight() = height.orEmpty()
    fun getSpecies() = species.orEmpty()
    fun getHomeWorld() = homeWorld
    fun getFilms() = films.orEmpty()
    fun getUrl() = url.orEmpty()
}