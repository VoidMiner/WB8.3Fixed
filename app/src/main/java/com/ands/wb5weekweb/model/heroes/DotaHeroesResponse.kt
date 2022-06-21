package com.ands.wb5weekweb.model.heroes

import com.squareup.moshi.Json
import java.io.Serializable

@kotlinx.serialization.Serializable
data class DotaHeroesResponse(
    @field: Json(name = "hero_id") val heroId: Int,
    val icon: String,
    val id: Int,
    val img: String,
    @field: Json(name = "localized_name") val localizedName: String,
    val name: String,
    @field: Json(name = "base_int") val baseInt: Int,
    @field: Json(name = "base_str") val baseStrength: Double,
    @field: Json(name = "move_speed") val moveSpeed: Double,
): Serializable