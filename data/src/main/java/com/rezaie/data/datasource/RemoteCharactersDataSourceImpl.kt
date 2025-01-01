package com.rezaie.data.datasource

import com.rezaie.data.api.remote.CharacterApiRemoteService
import com.rezaie.data.extenssion.bodyOrThrow
import com.rezaie.data.response.CharactersResponse
import com.rezaie.data.response.GetCharactersResponse
import kotlinx.serialization.json.Json
import javax.inject.Inject

class RemoteCharactersDataSourceImpl @Inject constructor(
    private val remoteService: CharacterApiRemoteService,
    private val json: Json
) : RemoteCharactersDataSource {
    override suspend fun getCharacters(search: String, page: String?): GetCharactersResponse {
        return remoteService.getCharacters(
            search, page
        ).bodyOrThrow(json)
    }
}

