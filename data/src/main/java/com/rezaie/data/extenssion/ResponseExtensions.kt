package com.rezaie.data.extenssion

import com.rezaie.core.SnappFoodException
import com.rezaie.data.response.ErrorResponse
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
    return SnappFoodException(errorResponse?.error?.message.orEmpty())
}