package com.rezaie.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.rezaie.core.IoDispatcher
import com.rezaie.data.api.local.db.core.CharacterDataBase
import com.rezaie.data.api.local.db.entity.CharacterTable
import com.rezaie.data.datasource.LocalCharactersDataSource
import com.rezaie.data.datasource.RemoteCharactersDataSource
import com.rezaie.data.mappers.toCharacterTable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator(
    private val database: CharacterDataBase,
    private val remoteDataSource: RemoteCharactersDataSource,
    private val localDataSource: LocalCharactersDataSource,
    private val pagingConfig: PagingConfig,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val search: String,
) : RemoteMediator<Int, CharacterTable>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterTable>
    ): MediatorResult {
        return try {
            return withContext(dispatcher) {
                val loadKey: Int = when (loadType) {
                    LoadType.REFRESH -> 0

                    LoadType.PREPEND -> return@withContext MediatorResult.Success(
                        endOfPaginationReached = true
                    )

                    LoadType.APPEND -> {
                        localDataSource
                            .getCharactersCount(search) / pagingConfig.pageSize
                    }
                }

                val response =
                    remoteDataSource.getCharacters(
                        search = search,
                        page = (loadKey + 1).toString(),
                    )


                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        localDataSource.deleteByQuery(query = search)
                    }
                    localDataSource.save(
                        response.results?.map { it.toCharacterTable().copy(query = search) }.orEmpty()
                    )
                }
                MediatorResult.Success(endOfPaginationReached = response.next == null)
            }
        } catch (e: IOException) {
            MediatorResult.Success(endOfPaginationReached = true)
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}
