package com.rezaie.data.mappers

import com.rezaie.data.api.local.db.entity.CharacterTable
import com.rezaie.data.response.CharactersResponse
import com.rezaie.domain.domainCharacteres.entity.CharacterEntity
import kotlin.random.Random

fun CharactersResponse.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id ?: Regex(".*/(\\d+)/").find(url.toString())?.groupValues?.get(1)?.toInt()
        ?: Random.nextInt(),
        name = name,
        birthYear = birthYear,
        height = height,
        films = films,
        species = species,
        homeWorld = homeWorld,
        url = url
    )
}

fun CharactersResponse.toCharacterTable(): CharacterTable {
    return CharacterTable(
        characterId = Regex(".*/(\\d+)/").find(url.toString())?.groupValues?.get(1)?.toInt()
            ?: Random.nextInt(),
        name = name,
        height = height,
        films = films,
        birthYear = birthYear,
        species = species,
        homeWorld = homeWorld,
        url = url,
        query = query
    )
}

fun CharacterTable.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = characterId,
        name = name,
        height = height,
        films = films,
        birthYear = birthYear,
        species = species,
        homeWorld = homeWorld,
        url = url
    )
}