package com.rezaie.data.api.remote

import com.rezaie.data.response.FilmsResponse
import com.rezaie.data.response.GetCharactersResponse
import com.rezaie.data.response.PlanetResponse
import com.rezaie.data.response.SpeciesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.rezaie.data.api.remote.CharacterApiRemoteService.Companion.FILM_PREFIX as FILM_PREFIX1

interface CharacterApiRemoteService {
    @GET(PEOPLE_PREFIX)
    suspend fun getCharacters(
        @Query("search") search: String,
        @Query("page") page: String?,
    ): Response<GetCharactersResponse>

    @GET("$FILM_PREFIX/{id}")
    suspend fun getFilm(
        @Path("id") id: String
    ): Response<FilmsResponse>

    @GET("$PLANET_PREFIX{id}")
    suspend fun getPlanet(
        @Path("id") id: String
    ): Response<PlanetResponse>

    @GET("$SPECIES_PREFIX{id}")
    suspend fun getSpecies(
        @Path("id") id: String
    ): Response<SpeciesResponse>

    companion object {
        private const val PEOPLE_PREFIX = "people/"
        private const val FILM_PREFIX = "films/"
        private const val PLANET_PREFIX = "planets/"
        private const val SPECIES_PREFIX = "species/"
    }
}