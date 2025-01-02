package com.rezaie.domain.domainCharacteres.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.rezaie.domain.domainCharacteres.entity.CharacterDetailEntity
import com.rezaie.domain.domainCharacteres.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(query: String, pagingConfig: PagingConfig): Flow<PagingData<CharacterEntity>>
    fun getCharacterDetail(characterEntity: CharacterEntity): Flow<CharacterDetailEntity?>
}