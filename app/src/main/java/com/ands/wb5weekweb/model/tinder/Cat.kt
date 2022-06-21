package com.ands.wb5weekweb.model.tinder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity(tableName = "cat_data")
@Serializable
data class Cat(
    @ColumnInfo(name = "breeds")
    val breeds: List<String>,
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "width")
    val width: Int,
    @ColumnInfo(name = "height")
    val height: Int,
)