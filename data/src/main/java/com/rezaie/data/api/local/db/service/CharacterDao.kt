package com.rezaie.data.api.local.db.service

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rezaie.data.api.local.db.entity.CharacterTable
import com.rezaie.domain.domainCharacteres.base.BaseDao

@Dao
interface CharacterDao : BaseDao<CharacterTable> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: List<CharacterTable>): List<Long>

    @Query("SELECT * FROM CharacterTable")
    fun getAsFlow(): PagingSource<Int, CharacterTable>

    @Query("SELECT * FROM CharacterTable WHERE `query` = :query")
    fun getCharactersByQuery(query: String): PagingSource<Int, CharacterTable>

    @Query("DELETE FROM CharacterTable")
    suspend fun deleteAll()

    @Delete
    override suspend fun delete(entity: CharacterTable)

    @Query("DELETE FROM CharacterTable WHERE `query` = :query")
    suspend fun deleteByQuery(query: String)

    @Query("SELECT COUNT(characterId) FROM CharacterTable WHERE `query` = :query")
    suspend fun getCharactersCount(query: String): Int
}