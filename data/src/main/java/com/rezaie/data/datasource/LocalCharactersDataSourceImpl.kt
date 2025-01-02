package com.rezaie.data.datasource

import androidx.paging.PagingSource
import com.rezaie.data.api.local.db.entity.CharacterTable
import com.rezaie.data.api.local.db.service.CharacterDao
import javax.inject.Inject

class LocalCharactersDataSourceImpl @Inject constructor(
    private val characterDao: CharacterDao,
) : LocalCharactersDataSource {
    override fun getAsFlow(query: String): PagingSource<Int, CharacterTable> {
        return characterDao.getCharactersByQuery(query)
    }

    override suspend fun save(list: List<CharacterTable>) {
        characterDao.insert(list)
    }

    override suspend fun delete() {
        characterDao.deleteAll()
    }

    override suspend fun deleteByQuery(query: String) {
        characterDao.deleteByQuery(query)
    }

    override suspend fun getCharactersCount(query: String): Int {
        return characterDao.getCharactersCount(query)
    }
}