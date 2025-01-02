package com.rezaie.data.api.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(
    tableName = "CharacterTable"
)
data class CharacterTable(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "characterId")
    val characterId: Int,
    @ColumnInfo(name = "id")
    val id: Int = 0,
    val name: String?,
    @ColumnInfo(name = "birthYear")
    val birthYear: String?,
    @ColumnInfo(name = "height")
    val height: String?,
    @ColumnInfo(name = "speciesData")
    @TypeConverters val species: List<String>? = null,
    @ColumnInfo(name = "planet")
    val homeWorld: String? = null,
    @ColumnInfo(name = "filmsData")
    @TypeConverters val films: List<String>? = null,
    @ColumnInfo(name = "url")
    val url: String?,
    @ColumnInfo(name = "query")
    val query: String? = ""
)
