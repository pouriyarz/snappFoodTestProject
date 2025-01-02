package com.rezaie.domain.domainCharacteres.entity

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
    fun getId() = id ?: 0
    fun getName() = name ?: ""
    fun getBirthYear() = birthYear ?: ""
    fun getHeight() = height ?: ""
    fun getSpecies() = species.orEmpty()
    fun getHomeWorld() = homeWorld
    fun getFilms() = films.orEmpty()
    fun getUrl() = url ?: ""
}