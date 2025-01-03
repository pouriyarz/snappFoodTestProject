package com.rezaie.feature.mapper

import com.rezaie.domain.domainCharacteres.entity.CharacterDetailEntity
import com.rezaie.domain.domainCharacteres.entity.CharacterEntity
import com.rezaie.domain.domainCharacteres.entity.FilmsEntity
import com.rezaie.domain.domainCharacteres.entity.PlanetEntity
import com.rezaie.domain.domainCharacteres.entity.SpeciesEntity
import com.rezaie.feature.presentation.CharacterDetailView
import com.rezaie.feature.presentation.CharacterView
import com.rezaie.feature.presentation.FilmView
import com.rezaie.feature.presentation.PlanetView
import com.rezaie.feature.presentation.SpeciesView

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

fun FilmsEntity.toFilmView(): FilmView =
    FilmView(
        id = getId(),
        name = getName(),
        openingCrawl = getDescription()
    )


fun PlanetEntity.toPlanetView(): PlanetView =
    PlanetView(
        id = getId(),
        population = getPopulation()
    )


fun SpeciesEntity.toSpeciesView(): SpeciesView =
    SpeciesView(
        id = getId(),
        name = getName(),
        language = getLanguage()
    )


fun CharacterDetailEntity.toCharacterDetailView(): CharacterDetailView =
    CharacterDetailView(
        id = getId(),
        name = getName(),
        birthYear = getBirthYear(),
        height = getHeight(),
        films = getFilms().map { it.toFilmView() },
        species = getSpecies().map { it.toSpeciesView() },
        homeWorld = getHomeWorld()?.toPlanetView(),
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