package com.ands.wb5weekweb.utils

import android.util.Base64.encodeToString
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.ands.wb5weekweb.model.tinder.Cat
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Database(entities = [Cat::class], version = 2)
@TypeConverters(Converters::class)
abstract class TinderDataBase : RoomDatabase() {
    abstract fun getDao(): Dao
}
class Converters {
    @TypeConverter
    fun fromList(value : List<String>) = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<List<String>>(value)
}