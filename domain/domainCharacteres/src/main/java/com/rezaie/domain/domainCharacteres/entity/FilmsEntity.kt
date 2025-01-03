package com.rezaie.domain.domainCharacteres.entity

import com.rezaie.components.extenssions.orZero

data class FilmsEntity(
    private var id: Int?,
    private val name: String?,
    private val description: String?
) {
    fun getId() = id.orZero()
    fun getName() = name.orEmpty()
    fun getDescription() = description.orEmpty()
}
