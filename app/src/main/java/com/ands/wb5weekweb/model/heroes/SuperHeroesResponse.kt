package com.ands.wb5weekweb.model.heroes

import com.google.gson.annotations.SerializedName


data class SuperHeroesResponse(
    val id: String,
    val name: String,
    val images: Images,
    @SerializedName("powerstats", alternate = arrayOf("powerStats", "PowerStats"))
    val powerstats: PowerStats//не хочет рабоатть с другим именем..поэтому оставил powerstats

)

data class PowerStats(
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
    val combat: String,
)

data class Images(
    val xs: String,
    val sm: String,
    val md: String,
    val lg: String,
)
