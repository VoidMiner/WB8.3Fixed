package com.ands.wb5weekweb.usecases

import com.ands.wb5weekweb.model.heroes.DotaHeroesResponse
import com.ands.wb5weekweb.repository.Repository


class CacheDotaHeroesUseCase(private val repository: Repository) {

    fun saveDotaHeroes(list: List<DotaHeroesResponse>) {
        repository.cacheDotaHeroes(list = list)
    }

    fun getDotaHeroes() : List<DotaHeroesResponse> {
        return repository.getCacheDotaHeroes()
    }

}