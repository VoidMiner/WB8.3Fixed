package com.ands.wb5weekweb.usecases.tinder

import com.ands.wb5weekweb.model.tinder.Cat
import com.ands.wb5weekweb.repository.Repository
import retrofit2.Response


class GetCatUseCase(private val repository: Repository) {

    suspend fun getCat(): Response<List<Cat>> {
        return repository.getCat()
    }

    suspend fun getCatKtor() : List<Cat> {
        return repository.getCatKtor()
    }

}