package com.rezaie.data.repository

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
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    val search: String,
) : RemoteMediator<Int, CharacterTable>() {

    var data: String? = null
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterTable>
    ): MediatorResult {
        return try {
            return withContext(dispatcher) {
                val loadKey: Int = when (loadType) {
                    LoadType.REFRESH -> {
                        myCurrentPage = 0
                        0
                    }

                    LoadType.PREPEND -> return@withContext MediatorResult.Success(
                        endOfPaginationReached = true
                    )

                    LoadType.APPEND -> {
                        myCurrentPage
                    }
                }

                val response =
                    remoteDataSource.getCharacters(
                        search = search,
                        page = data,
                    )

                data = response.next

                myCurrentPage = myCurrentPage.plus(1)

                database.withTransaction {
                    localDataSource.save(
                        response.results?.map { it.toCharacterTable() }.orEmpty()
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

    companion object {
        var myCurrentPage = 0
    }
}
