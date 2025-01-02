package com.rezaie.data.api.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity(
    tableName = "SpeciesTable",
    foreignKeys = [
        ForeignKey(
            entity = CharacterTable::class,
            parentColumns = ["characterId"],
            childColumns = ["characterId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["characterId"])]
)
data class SpeciesTable(
    @ColumnInfo(name = "characterId")
    val characterId: Int = 0,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "speciesId")
    val speciesId: Int = 0,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "language")
    val language: String?
)
