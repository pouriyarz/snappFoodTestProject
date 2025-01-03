package com.rezaie.domain.domainCharacteres.entity

import com.rezaie.components.extenssions.orZero

data class CharacterEntity(
    private val id: Int?,
    private val name: String?,
    private val birthYear: String?,
    private val height: String?,
    private val species: List<String>?,
    private val homeWorld: String?,
    private val films: List<String>?,
    private val url: String?
) {
    fun getId() = id.orZero()
    fun getName() = name.orEmpty()
    fun getBirthYear() = birthYear.orEmpty()
    fun getHeight() = height.orEmpty()
    fun getSpecies() = species
    fun getHomeWorld() = homeWorld.orEmpty()
    fun getFilms() = films
    fun getUrl() = url
}