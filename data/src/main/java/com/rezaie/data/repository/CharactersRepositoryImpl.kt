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
import com.rezaie.data.mappers.toCharacterEntity
import com.rezaie.domain.domainCharacteres.entity.CharacterEntity
import com.rezaie.domain.domainCharacteres.repository.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val characterDataBase: CharacterDataBase,
    private val remoteDataSource: RemoteCharactersDataSource,
    private val localDataSource: LocalCharactersDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : CharacterRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getCharacters(query: String): Flow<PagingData<CharacterEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = 9,
                enablePlaceholders = true,
                prefetchDistance = 0
            ),
            remoteMediator = CharactersRemoteMediator(
                database = characterDataBase,
                remoteDataSource = remoteDataSource,
                localDataSource = localDataSource,
                pagingConfig = PagingConfig(
                    pageSize = PAGE_SIZE,
                    initialLoadSize = 9,
                    enablePlaceholders = true,
                    prefetchDistance = 0
                ),
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

    companion object {
        private const val PAGE_SIZE = 20
    }
}