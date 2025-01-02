package com.rezaie.data.api.local.db.service

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.rezaie.data.api.local.db.entity.CharacterDetailTable
import com.rezaie.data.api.local.db.entity.CharacterDetailWithRelations
import com.rezaie.data.api.local.db.entity.CharacterTable
import com.rezaie.data.api.local.db.entity.FilmTable
import com.rezaie.data.api.local.db.entity.PlanetTable
import com.rezaie.data.api.local.db.entity.SpeciesTable
import com.rezaie.domain.domainCharacteres.base.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao : BaseDao<CharacterTable> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(obj: List<CharacterTable>): List<Long>

    @Query("SELECT * FROM CharacterTable")
    fun getAsFlow(): PagingSource<Int, CharacterTable>

    @Query("SELECT * FROM CharacterTable WHERE `query` = :query")
    fun getCharactersByQuery(query: String): PagingSource<Int, CharacterTable>

    @Query("DELETE FROM CharacterTable")
    suspend fun deleteAllCharacters()

    @Delete
    override suspend fun delete(entity: CharacterTable)

    @Query("DELETE FROM CharacterTable WHERE `query` = :query")
    suspend fun deleteByQuery(query: String)

    @Query("SELECT COUNT(characterId) FROM CharacterTable WHERE `query` = :query")
    suspend fun getCharactersCount(query: String): Int


    @Transaction
    @Query("SELECT * FROM CharacterTable WHERE characterId = :characterId")
    fun getCharacterDetailWithRelatedData(characterId: Int): Flow<CharacterDetailTable?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilms(obj: List<FilmTable>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlanets(obj: PlanetTable): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecies(obj: List<SpeciesTable>): List<Long>

    @Query("DELETE FROM FilmTable WHERE characterId = :characterId")
    suspend fun deleteAllFilms(characterId: Int)

    @Query("DELETE FROM PlanetTable WHERE characterId = :characterId")
    suspend fun deleteAllPlanets(characterId: Int)

    @Query("DELETE FROM SpeciesTable WHERE characterId = :characterId")
    suspend fun deleteAllSpecies(characterId: Int)
}