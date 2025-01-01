package com.rezaie.data.datasource

import com.rezaie.data.response.CharactersResponse
import com.rezaie.data.response.GetCharactersResponse


interface RemoteCharactersDataSource {
    suspend fun getCharacters(
        search: String,
        page: String?
    ): GetCharactersResponse
}
