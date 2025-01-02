package com.rezaie.data.api.local.db.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rezaie.data.api.local.db.convertors.StringConvertor
import com.rezaie.data.api.local.db.entity.CharacterDetailTable
import com.rezaie.data.api.local.db.entity.CharacterTable
import com.rezaie.data.api.local.db.entity.FilmTable
import com.rezaie.data.api.local.db.entity.PlanetTable
import com.rezaie.data.api.local.db.entity.SpeciesTable
import com.rezaie.data.api.local.db.service.CharacterDao

@Database(
    entities = [
        CharacterTable::class,
        FilmTable::class,
        PlanetTable::class,
        SpeciesTable::class,
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    StringConvertor::class
)

abstract class CharacterDataBase : RoomDatabase() {
    abstract fun getCharacterDao(): CharacterDao
}
