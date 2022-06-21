package com.ands.wb5weekweb.model.tinder

import kotlinx.serialization.Serializable


@Serializable
data class LikedCats(
    val id: String,
    val image_id: String,
    val value: Int
)