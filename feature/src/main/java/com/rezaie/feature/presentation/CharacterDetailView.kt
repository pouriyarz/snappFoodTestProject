package com.rezaie.feature.presentation

data class CharacterDetailView(
    val id: Int,
    val name: String,
    val birthYear: String,
    val height: String,
    val species: List<SpeciesView>,
    val homeWorld: PlanetView?,
    val films: List<FilmView>,
    val url: String
) {
    companion object {
        fun empty(): CharacterDetailView =
            CharacterDetailView(
                id = 0,
                name = "",
                birthYear = "",
                height = "",
                species = listOf(),
                homeWorld = null,
                films = listOf(),
                url = "",
            )
    }
}