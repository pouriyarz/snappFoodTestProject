package com.rezaie.data.mappers

import com.rezaie.components.extenssions.getIdFromUrl
import com.rezaie.data.api.local.db.entity.CharacterDetailTable
import com.rezaie.data.api.local.db.entity.CharacterDetailWithRelations
import com.rezaie.data.api.local.db.entity.CharacterTable
import com.rezaie.data.api.local.db.entity.FilmTable
import com.rezaie.data.api.local.db.entity.PlanetTable
import com.rezaie.data.api.local.db.entity.SpeciesTable
import com.rezaie.data.response.CharactersResponse
import com.rezaie.data.response.FilmsResponse
import com.rezaie.data.response.PlanetResponse
import com.rezaie.data.response.SpeciesResponse
import com.rezaie.domain.domainCharacteres.entity.CharacterDetailEntity
import com.rezaie.domain.domainCharacteres.entity.CharacterEntity
import com.rezaie.domain.domainCharacteres.entity.FilmsEntity
import com.rezaie.domain.domainCharacteres.entity.PlanetEntity
import com.rezaie.domain.domainCharacteres.entity.SpeciesEntity
import kotlin.random.Random

fun CharactersResponse.toCharacterEntity(): CharacterEntity {
    return CharacterEntity(
        id = id ?: url?.getIdFromUrl()?.toInt()
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

fun FilmsResponse.toFilmEntity(): FilmsEntity {
    return FilmsEntity(
        id = id,
        name = name,
        description = openingCrawl
    )
}

fun PlanetResponse.toPlanetEntity(): PlanetEntity {
    return PlanetEntity(
        id = id,
        population = population
    )
}

fun SpeciesResponse.toSpeciesEntity(): SpeciesEntity {
    return SpeciesEntity(
        id = id,
        name = name,
        language = language
    )
}

fun FilmsResponse.toFilmsTable(): FilmTable {
    return FilmTable(
        name = name,
        openingCrawl = openingCrawl
    )
}

fun PlanetResponse.toPlanetTable(): PlanetTable {
    return PlanetTable(
        population = population
    )
}

fun SpeciesResponse.toSpeciesTable(): SpeciesTable {
    return SpeciesTable(
        name = name,
        language = language
    )
}

fun CharactersResponse.toCharacterTable(): CharacterTable {
    return CharacterTable(
        characterId = url?.getIdFromUrl()?.toInt()
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

fun FilmTable.toFilmEntity(): FilmsEntity {
    return FilmsEntity(
        id = filmId,
        name = name,
        description = openingCrawl
    )
}

fun PlanetTable.toPlanetEntity(): PlanetEntity {
    return PlanetEntity(
        id = planetId,
        population = population
    )
}

fun SpeciesTable.toSpeciesEntity(): SpeciesEntity {
    return SpeciesEntity(
        id = speciesId,
        name = name,
        language = language,
    )
}

fun PlanetEntity.toPlanetTable(): PlanetTable {
    return PlanetTable(
        population = getPopulation()
    )
}

fun FilmsEntity.toFilmTable(): FilmTable {
    return FilmTable(
        name = getName(),
        openingCrawl = getDescription()
    )
}

fun SpeciesEntity.toSpeciesTable(): SpeciesTable {
    return SpeciesTable(
        name = getName(),
        language = getLanguage()
    )
}

fun CharacterDetailTable.toCharacterDetailEntity(): CharacterDetailEntity {
    return CharacterDetailEntity(
        id = character.characterId,
        name = character.name,
        height = character.height,
        films = films?.map { it.toFilmEntity() },
        birthYear = character.birthYear,
        species = species?.map { it.toSpeciesEntity() },
        homeWorld = planet?.toPlanetEntity(),
        url = character.url
    )
}

fun CharacterDetailEntity.toCharacterTable(): CharacterDetailTable {
    return CharacterDetailTable(
        character = CharacterTable(
        characterId = this.getId(),
        name = this.getName(),
        birthYear = this.getBirthYear(),
        height = this.getHeight(),
        url = this.getUrl(),
        ),
        planet = getHomeWorld()?.toPlanetTable(),
        species = getSpecies()?.map { it.toSpeciesTable() },
        films = getFilms()?.map { it.toFilmTable() }
    )
}