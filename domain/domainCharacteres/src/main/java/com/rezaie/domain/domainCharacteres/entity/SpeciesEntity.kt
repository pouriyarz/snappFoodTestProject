package com.rezaie.domain.domainCharacteres.entity

import com.rezaie.components.extenssions.orZero

data class SpeciesEntity(
    private val id: Int?,
    private val language: String?,
    private val name: String?,
) {
    fun getId() = id.orZero()
    fun getName() = name.orEmpty()
    fun getLanguage() = language.orEmpty()
}
