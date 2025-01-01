package com.rezaie.data.api.remote

import com.rezaie.data.response.GetCharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApiRemoteService {
    @GET("$PEOPLE_PREFIX")
    suspend fun getCharacters(
        @Query("search") search: String,
        @Query("page") page: String?,
    ): Response<GetCharactersResponse>

    companion object {
        private const val PEOPLE_PREFIX = "people/"
    }
}