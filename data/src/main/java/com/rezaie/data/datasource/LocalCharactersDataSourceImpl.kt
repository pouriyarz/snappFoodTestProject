package com.rezaie.data.datasource

import androidx.paging.PagingSource
import com.rezaie.data.api.local.db.entity.CharacterTable
import com.rezaie.data.api.local.db.service.CharacterDao
import javax.inject.Inject

class LocalCharactersDataSourceImpl @Inject constructor(
    private val characterDao: CharacterDao,
) : LocalCharactersDataSource {
    override fun getAsFlow(): PagingSource<Int, CharacterTable> {
        return characterDao.getAsFlow()
    }

    override suspend fun save(list: List<CharacterTable>) {
        characterDao.insert(list)
    }

    override suspend fun delete() {
        characterDao.deleteAll()
    }
}