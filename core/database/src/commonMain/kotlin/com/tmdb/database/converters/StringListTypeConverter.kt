package com.tmdb.database.converters

/**
 * Created by van.luong
 * on 17,June,2025
 */
import androidx.room.TypeConverter
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

class StringListTypeConverter {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return list?.let {
            json.encodeToString(ListSerializer(String.serializer()), it)
        }
    }

    @TypeConverter
    fun toStringList(data: String?): List<String>? {
        return data?.let {
            json.decodeFromString(ListSerializer(String.serializer()), it)
        }
    }
}
