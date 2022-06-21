package com.ands.wb5weekweb.api

import com.ands.wb5weekweb.model.heroes.SuperHeroesResponse
import retrofit2.Response
import retrofit2.http.GET


interface ApiServiceSuperHeroes {

    @GET("all.json")
    suspend fun getSuperHeroes(): List<SuperHeroesResponse>
}