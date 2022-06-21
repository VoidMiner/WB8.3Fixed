package com.ands.wb5weekweb.usecases.tinder

import com.ands.wb5weekweb.model.tinder.VoteRequest
import com.ands.wb5weekweb.model.tinder.VoteResponse
import com.ands.wb5weekweb.repository.Repository
import retrofit2.Response


class CreateVoteUseCase(private val repository: Repository) {

    suspend fun createVote(voteRequest: VoteRequest): Response<VoteResponse> {
        return repository.createVote(voteRequest = voteRequest)
    }

}