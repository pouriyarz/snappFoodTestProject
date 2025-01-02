package com.rezaie.domain.domainCharacteres.entity

data class FilmsEntity(
    private var id: Int?,
    private val name: String?,
    private val description: String?
) {
    fun getId() = id ?: 0
    fun getName() = name ?: ""
    fun getDescription() = description ?: ""
}
