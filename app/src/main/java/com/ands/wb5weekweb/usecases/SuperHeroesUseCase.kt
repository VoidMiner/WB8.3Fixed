package com.ands.wb5weekweb.usecases

import com.ands.wb5weekweb.model.heroes.SuperHeroesResponse
import com.ands.wb5weekweb.repository.Repository


class SuperHeroesUseCase(private val repository: Repository) {

    suspend fun getHeroes(): List<SuperHeroesResponse> {
        return repository.getSuperHeroes()
    }

    fun isCacheExist() : Boolean {
        return repository.isCacheExist()
    }

    fun loadCacheHeroesList() : List<SuperHeroesResponse> {
        return repository.getSavedSuperHeroes()
    }

    fun saveHeroes(list: List<SuperHeroesResponse>) {
        repository.saveSuperHeroes(superHeroes = list)
    }
}

