package com.rezaie.domain.domainCharacteres.entity

data class SpeciesEntity(
    private val id: Int?,
    private val language: String?,
    private val name: String?,
) {
    fun getId() = id ?: 0
    fun getName() = name ?: ""
    fun getLanguage() = language ?: ""
}
