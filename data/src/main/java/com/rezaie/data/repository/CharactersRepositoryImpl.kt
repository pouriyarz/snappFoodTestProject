package com.rezaie.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.rezaie.core.IoDispatcher
import com.rezaie.data.api.local.db.core.CharacterDataBase
import com.rezaie.data.datasource.LocalCharactersDataSource
import com.rezaie.data.datasource.RemoteCharactersDataSource
import com.rezaie.data.extenssion.firstOfflineWithCacheFlowDB
import com.rezaie.data.mappers.toCharacterDetailEntity
import com.rezaie.data.mappers.toCharacterEntity
import com.rezaie.data.mappers.toCharacterTable
import com.rezaie.data.mappers.toFilmTable
import com.rezaie.data.mappers.toPlanetTable
import com.rezaie.data.mappers.toSpeciesTable
import com.rezaie.domain.domainCharacteres.entity.CharacterDetailEntity
import com.rezaie.domain.domainCharacteres.entity.CharacterEntity
import com.rezaie.domain.domainCharacteres.repository.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val characterDataBase: CharacterDataBase,
    private val remoteDataSource: RemoteCharactersDataSource,
    private val localDataSource: LocalCharactersDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CharacterRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getCharacters(query: String, pagingConfig: PagingConfig): Flow<PagingData<CharacterEntity>> {
        return Pager(
            config = pagingConfig,
            remoteMediator = CharactersRemoteMediator(
                database = characterDataBase,
                remoteDataSource = remoteDataSource,
                localDataSource = localDataSource,
                pagingConfig = pagingConfig,
                dispatcher = dispatcher,
                search = query
            )
        )
        {
            localDataSource.getAsFlow(query)
        }.flow.map { pagingData ->
            pagingData.map { it.toCharacterEntity() }
        }
    }

    private suspend fun saveCharacterDetailsToDb(characterDetailEntity: CharacterDetailEntity) {
        withContext(dispatcher) {
            val characterId = characterDetailEntity.getId()
            localDataSource.saveFilms(
                characterDetailEntity.getFilms()
                    .map { it.toFilmTable().copy(characterId = characterId) })
            localDataSource.saveSpecies(
                characterDetailEntity.getSpecies()
                    .map { it.toSpeciesTable().copy(characterId = characterId) })
            localDataSource.savePlanets(
                characterDetailEntity.getHomeWorld()?.toPlanetTable()
                    ?.copy(characterId = characterId)
            )
        }
    }

    override fun getCharacterDetail(characterEntity: CharacterEntity): Flow<CharacterDetailEntity?> {
        return firstOfflineWithCacheFlowDB(
            createCall = { remoteDataSource.fetchCharacterDetailsFromApi(characterEntity) },
            loadFromLocal = { localDataSource.getCharacterDetail(characterEntity) },
            shouldFetch = { true },
            saveCallResult = { characterDetail ->
                saveCharacterDetailsToDb(characterDetail.toCharacterDetailEntity())
            },
            remoteToLocalMapper = { remoteCharacterDetail -> remoteCharacterDetail.toCharacterTable() },
            localToDomainMapper = { localCharacterTable -> localCharacterTable?.toCharacterDetailEntity() }
        )
    }
}