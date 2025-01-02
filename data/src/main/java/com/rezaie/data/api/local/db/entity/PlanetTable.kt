package com.rezaie.data.api.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlin.random.Random

@Entity(
    tableName = "PlanetTable",
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
data class PlanetTable(
    @ColumnInfo(name = "characterId")
    val characterId: Int = 0,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "planetId")
    val planetId: Int = 0,
    @ColumnInfo(name = "population")
    val population: String?
)
