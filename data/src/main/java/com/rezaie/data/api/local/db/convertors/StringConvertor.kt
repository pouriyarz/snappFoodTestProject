package com.rezaie.data.api.local.db.convertors

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

@ProvidedTypeConverter
class StringConvertor @Inject constructor(private val json: Json) {
    @TypeConverter
    fun toJson(response: List<String>): String {
        return json.encodeToString(response)
    }

    @TypeConverter
    fun fromJson(text: String): List<String> {
        return json.decodeFromString(text)
    }
}