package com.rezaie.domain.domainCharacteres.repository

import androidx.paging.PagingData
import com.rezaie.domain.domainCharacteres.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(query: String): Flow<PagingData<CharacterEntity>>

}