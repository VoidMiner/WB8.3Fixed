package com.ands.wb5weekweb.model.tinder


data class VoteRequest(
    val image_id: String,
    val sub_id: String,
    val value: Int
)