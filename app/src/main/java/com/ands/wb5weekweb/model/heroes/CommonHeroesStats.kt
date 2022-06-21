package com.ands.wb5weekweb.model.heroes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CommonHeroesStats(
    val id: String,
    val image: String,
    val movementSpeed: String,
    val baseInt: String,
    val baseStrength: String,
    val name: String
) : Parcelable

