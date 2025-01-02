package com.rezaie.data.api.local.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CharacterDetailWithRelations(
    @Embedded val characterDetail: CharacterDetailTable,

    @Relation(
        parentColumn = "characterId",
        entityColumn = "characterId"
    )
    val species: List<SpeciesTable>,

    @Relation(
        parentColumn = "characterId",
        entityColumn = "characterId"
    )
    val homeWorld: PlanetTable,

    @Relation(
        parentColumn = "characterId",
        entityColumn = "characterId"
    )
    val films: List<FilmTable>
)
