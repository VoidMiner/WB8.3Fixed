package com.ands.wb5weekweb.utils

import androidx.room.*
import androidx.room.Dao
import com.ands.wb5weekweb.model.tinder.Cat

@Dao
interface Dao {

    @Insert
    suspend fun insertCat(cat: Cat)

    @Query("SELECT EXISTS(SELECT * FROM cat_data)")
    suspend fun isExists(): Boolean

    @Query("SELECT * FROM cat_data")
    suspend fun getCatCache(): Cat

    @Query("DELETE FROM cat_data")
    suspend fun deleteCat()

}