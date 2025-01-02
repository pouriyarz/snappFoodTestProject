package com.rezaie.data.api.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

data class CharacterDetailTable(
    @Embedded
    val character: CharacterTable, // The main character data

    @Relation(
        parentColumn = "characterId",
        entityColumn = "characterId"
    )
    val films: List<FilmTable>?, // List of related films

    @Relation(
        parentColumn = "characterId",
        entityColumn = "characterId"
    )
    val planet: PlanetTable?, // List of related species

    @Relation(
        parentColumn = "characterId",
        entityColumn = "characterId"
    )
    val species: List<SpeciesTable>? // List of related species
)
