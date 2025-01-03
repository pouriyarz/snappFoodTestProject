package com.rezaie.data.extenssion

import com.rezaie.core.SnappFoodException
import com.rezaie.data.response.ErrorResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json
import retrofit2.Response

internal fun <T> Response<T>.bodyOrThrow(json: Json): T {
    if (isSuccessful) {
        return body()!!
    } else {
        throw parseError(json)
    }
}

internal fun <T> Response<T>.parseError(json: Json): Exception {
    val errorResponse = errorBody()?.string()?.let { json.decodeFromString<ErrorResponse>(it) }
    return SnappFoodException(errorResponse?.error.orEmpty())
}

inline fun <R, L, D> firstOfflineWithCacheFlowDB(
    crossinline createCall: suspend () -> R,
    crossinline loadFromLocal: suspend () -> Flow<L?>,
    crossinline shouldFetch: suspend (L?) -> Boolean,
    crossinline saveCallResult: suspend (L) -> Unit,
    crossinline remoteToLocalMapper: (R) -> L,
    crossinline localToDomainMapper: (L?) -> D
): Flow<D> {
    return channelFlow {
        val cache = loadFromLocal().first()
        val shouldFetchFromDb = shouldFetch(cache)
        if (cache != null) send(localToDomainMapper(cache))

        if (shouldFetchFromDb) {
            val result = createCall()
            val remoteResult = remoteToLocalMapper(result)
            saveCallResult(remoteResult)
            loadFromLocal().collectLatest {
                send(localToDomainMapper(it))
            }
        }
    }
}
