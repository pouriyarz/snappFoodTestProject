package com.rezaie.data.mappers

import com.rezaie.data.api.local.db.entity.CharacterTable
import com.rezaie.data.response.CharactersResponse
import com.rezaie.domain.domainCharacteres.entity.CharacterEntity
import kotlin.random.Random

fun CharactersResponse.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        birthYear = birthYear,
        height = height,
        films = films,
        species = species,
        homeWorld = homeWorld
    )
}

fun CharactersResponse.toCharacterTable(): CharacterTable {
    return CharacterTable(
        id = id ?: Random.nextInt(),
        name = name,
        height = height,
        films = films,
        birthYear = birthYear,
        species = species,
        homeWorld = homeWorld
    )
}

fun CharacterTable.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        height = height,
        films = films,
        birthYear = birthYear,
        species = species,
        homeWorld = homeWorld
    )
}