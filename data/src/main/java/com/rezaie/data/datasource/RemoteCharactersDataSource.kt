package com.rezaie.data.datasource

import com.rezaie.data.response.CharactersResponse
import com.rezaie.data.response.FilmsResponse
import com.rezaie.data.response.GetCharactersResponse
import com.rezaie.data.response.PlanetResponse
import com.rezaie.data.response.SpeciesResponse
import com.rezaie.domain.domainCharacteres.entity.CharacterDetailEntity
import com.rezaie.domain.domainCharacteres.entity.CharacterEntity
import com.rezaie.domain.domainCharacteres.entity.FilmsEntity


interface RemoteCharactersDataSource {
    suspend fun getCharacters(
        search: String,
        page: String?
    ): GetCharactersResponse

    suspend fun getFilms(
        filmUrls: List<String>?
    ): List<FilmsEntity>

    suspend fun getPlanets(
        id: String
    ): PlanetResponse

    suspend fun getSpecies(
        id: String
    ): SpeciesResponse

    suspend fun fetchCharacterDetailsFromApi(characterEntity: CharacterEntity): CharacterDetailEntity
}
