package com.rezaie.data.datasource

import androidx.paging.PagingSource
import com.rezaie.data.api.local.db.entity.CharacterDetailTable
import com.rezaie.data.api.local.db.entity.CharacterDetailWithRelations
import com.rezaie.data.api.local.db.entity.CharacterTable
import com.rezaie.data.api.local.db.entity.FilmTable
import com.rezaie.data.api.local.db.entity.PlanetTable
import com.rezaie.data.api.local.db.entity.SpeciesTable
import com.rezaie.data.api.local.db.service.CharacterDao
import com.rezaie.domain.domainCharacteres.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalCharactersDataSourceImpl @Inject constructor(
    private val characterDao: CharacterDao,
) : LocalCharactersDataSource {
    override fun getAsFlow(query: String): PagingSource<Int, CharacterTable> {
        return characterDao.getCharactersByQuery(query)
    }

    override suspend fun saveCharacters(list: List<CharacterTable>) {
        characterDao.insertCharacters(list)
    }

    override suspend fun saveFilms(list: List<FilmTable>) {
        list.firstOrNull()?.characterId?.let { characterDao.deleteAllFilms(it) }
        characterDao.insertFilms(list)
    }

    override suspend fun savePlanets(list: PlanetTable?) {
        list?.characterId?.let { characterDao.deleteAllPlanets(it) }
        if (list != null) {
            characterDao.insertPlanets(list)
        }
    }

    override suspend fun saveSpecies(list: List<SpeciesTable>) {
        list.firstOrNull()?.characterId?.let { characterDao.deleteAllSpecies(it) }
        characterDao.insertSpecies(list)
    }

    override suspend fun delete() {
        characterDao.deleteAllCharacters()
    }

    override suspend fun deleteByQuery(query: String) {
        characterDao.deleteByQuery(query)
    }

    override suspend fun getCharactersCount(query: String): Int {
        return characterDao.getCharactersCount(query)
    }

    override suspend fun getCharacterDetail(characterEntity: CharacterEntity): Flow<CharacterDetailTable?> {
        return characterDao.getCharacterDetailWithRelatedData(characterEntity.getId())
    }
}