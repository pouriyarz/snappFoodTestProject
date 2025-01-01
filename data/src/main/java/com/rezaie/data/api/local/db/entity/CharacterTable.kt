package com.rezaie.data.api.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(
    tableName = "CharacterTable"
)
data class CharacterTable(
    @ColumnInfo(name = "characterId")
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String?,
    @ColumnInfo(name = "birthYear")
    val birthYear: String?,
    @ColumnInfo(name = "height")
    val height: String?,
    @ColumnInfo(name = "species")
    @TypeConverters val species: List<String>?,
    @ColumnInfo(name = "homeWorld")
    val homeWorld: String?,
    @ColumnInfo(name = "films")
    @TypeConverters val films: List<String>?
)
