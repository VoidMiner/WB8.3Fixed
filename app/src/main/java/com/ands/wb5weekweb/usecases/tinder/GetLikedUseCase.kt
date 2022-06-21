package com.ands.wb5weekweb.usecases.tinder

import com.ands.wb5weekweb.model.tinder.Cat
import com.ands.wb5weekweb.model.tinder.LikedCats
import com.ands.wb5weekweb.repository.Repository
import retrofit2.Response


class GetLikedUseCase(private val repository: Repository) {

    suspend fun getLiked(subId: String) : Response<List<LikedCats>> {
        return repository.getLiked(subId = subId)
    }

    suspend fun getLikedKtor(subId: String): List<LikedCats> {
        return repository.getLikedKtor(subId= subId)
    }

}