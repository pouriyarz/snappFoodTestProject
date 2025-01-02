package com.rezaie.data.datasource

import androidx.paging.PagingSource
import com.rezaie.data.api.local.db.entity.CharacterTable

interface LocalCharactersDataSource {
    fun getAsFlow(query: String): PagingSource<Int, CharacterTable>
    suspend fun save(list: List<CharacterTable>)
    suspend fun delete()
    suspend fun deleteByQuery(query: String)
    suspend fun getCharactersCount(query: String): Int
}