package com.rezaie.data.datasource

import com.rezaie.components.extenssions.getIdFromUrl
import com.rezaie.core.IoDispatcher
import com.rezaie.data.api.remote.CharacterApiRemoteService
import com.rezaie.data.extenssion.bodyOrThrow
import com.rezaie.data.mappers.toFilmEntity
import com.rezaie.data.mappers.toPlanetEntity
import com.rezaie.data.mappers.toSpeciesEntity
import com.rezaie.data.response.FilmsResponse
import com.rezaie.data.response.GetCharactersResponse
import com.rezaie.data.response.PlanetResponse
import com.rezaie.data.response.SpeciesResponse
import com.rezaie.domain.domainCharacteres.entity.CharacterDetailEntity
import com.rezaie.domain.domainCharacteres.entity.CharacterEntity
import com.rezaie.domain.domainCharacteres.entity.FilmsEntity
import com.rezaie.domain.domainCharacteres.entity.PlanetEntity
import com.rezaie.domain.domainCharacteres.entity.SpeciesEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class RemoteCharactersDataSourceImpl @Inject constructor(
    private val remoteService: CharacterApiRemoteService,
    private val json: Json,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : RemoteCharactersDataSource {
    override suspend fun getCharacters(search: String, page: String?): GetCharactersResponse {
        return remoteService.getCharacters(
            search, page
        ).bodyOrThrow(json)
    }

    override suspend fun getFilms(filmUrls: List<String>?): List<FilmsEntity> {
        return withContext(dispatcher) {
            if (filmUrls.isNullOrEmpty()) return@withContext emptyList()

            val filmDeferreds = filmUrls.map { filmUrl ->
                val id = filmUrl.getIdFromUrl()
                async {
                    remoteService.getFilm(id).bodyOrThrow(json)
                }
            }
            val filmResponses = filmDeferreds.awaitAll()

            return@withContext filmResponses.map { it.toFilmEntity() }
        }
    }

    override suspend fun getPlanets(id: String): PlanetResponse {
        return remoteService.getPlanet(id).bodyOrThrow(json)
    }

    override suspend fun getSpecies(id: String): SpeciesResponse {
        return remoteService.getSpecies(id).bodyOrThrow(json)
    }

    override suspend fun fetchCharacterDetailsFromApi(characterEntity: CharacterEntity): CharacterDetailEntity =
        withContext(dispatcher) {
            val characterId = characterEntity.getId().toString()

            val filmsDeferred = async { getFilms(characterEntity.getFilms()) }  // Pass film URLs directly
            val planetsDeferred = async { getPlanets(characterId) }
            val speciesDeferred = async { getSpecies(characterId) }

            val filmsResponse = filmsDeferred.await()
            val planetsResponse = planetsDeferred.await()
            val speciesResponse = speciesDeferred.await()

            val speciesEntities = listOf(speciesResponse.toSpeciesEntity())
            val planetEntity = planetsResponse.toPlanetEntity()

            return@withContext CharacterDetailEntity(
                id = characterEntity.getId(),
                name = characterEntity.getName(),
                birthYear = characterEntity.getBirthYear(),
                height = characterEntity.getHeight(),
                species = speciesEntities,
                homeWorld = planetEntity,
                films = filmsResponse,
                url = characterEntity.getUrl()
            )
        }
}

