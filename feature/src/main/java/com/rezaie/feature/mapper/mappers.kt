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

fun FilmView.toFilmsEntity(): FilmsEntity =
    FilmsEntity(
        id = id,
        name = name,
        description = openingCrawl
    )

fun PlanetView.toPlanetEntity(): PlanetEntity =
    PlanetEntity(
        id = id,
        population = population
    )

fun SpeciesView.toSpeciesEntity(): SpeciesEntity =
    SpeciesEntity(
        id = id,
        name = name,
        language = language
    )

fun CharacterDetailView.toCharacterDetailEntity(): CharacterDetailEntity =
    CharacterDetailEntity(
        id = id,
        name = name,
        birthYear = birthYear,
        height = height,
        films = films.map { it.toFilmsEntity() },
        species = species.map { it.toSpeciesEntity() },
        homeWorld = homeWorld?.toPlanetEntity(),
        url = url
    )