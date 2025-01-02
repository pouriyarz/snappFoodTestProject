package com.rezaie.feature.mapper

import com.rezaie.domain.domainCharacteres.entity.CharacterEntity
import com.rezaie.feature.presentation.CharacterView

fun CharacterEntity.toCharacterView(): CharacterView =
    CharacterView(
        id = getId(),
        name = getName(),
        birthYear = getBirthYear(),
        height = getHeight(),
        films = getFilms(),
        species = getSpecies(),
        homeWorld = getHomeWorld(),
        url = getUrl()
    )

fun CharacterView.toCharacterEntity(): CharacterEntity =
    CharacterEntity(
        id = id,
        name = name,
        birthYear = birthYear,
        height = height,
        films = films,
        species = species,
        homeWorld = homeWorld,
        url = url
    )