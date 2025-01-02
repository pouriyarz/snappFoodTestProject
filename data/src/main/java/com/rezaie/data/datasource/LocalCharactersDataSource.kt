package com.rezaie.data.datasource

import androidx.paging.PagingSource
import com.rezaie.data.api.local.db.entity.CharacterDetailTable
import com.rezaie.data.api.local.db.entity.CharacterDetailWithRelations
import com.rezaie.data.api.local.db.entity.CharacterTable
import com.rezaie.data.api.local.db.entity.FilmTable
import com.rezaie.data.api.local.db.entity.PlanetTable
import com.rezaie.data.api.local.db.entity.SpeciesTable
import com.rezaie.domain.domainCharacteres.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface LocalCharactersDataSource {
    fun getAsFlow(query: String): PagingSource<Int, CharacterTable>
    suspend fun saveCharacters(list: List<CharacterTable>)
    suspend fun saveFilms(list: List<FilmTable>)
    suspend fun savePlanets(list:PlanetTable?)
    suspend fun saveSpecies(list: List<SpeciesTable>)
    suspend fun delete()
    suspend fun deleteByQuery(query: String)
    suspend fun getCharactersCount(query: String): Int
    suspend fun getCharacterDetail(characterEntity: CharacterEntity): Flow<CharacterDetailTable?>
}