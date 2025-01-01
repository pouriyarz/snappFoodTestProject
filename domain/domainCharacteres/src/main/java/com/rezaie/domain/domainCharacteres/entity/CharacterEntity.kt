package com.rezaie.domain.domainCharacteres.entity

data class CharacterEntity(
    private val id: Int?,
    private val name: String?,
    private val birthYear: String?,
    private val height: String?,
    private val species: List<String>?,
    private val homeWorld: String?,
    private val films: List<String>?,
) {
    fun getId() = id
    fun getName() = name
    fun getBirthYear() = birthYear
    fun getHeight() = height
    fun getSpecies() = species
    fun getHomeWorld() = homeWorld
    fun getFilms() = films
}