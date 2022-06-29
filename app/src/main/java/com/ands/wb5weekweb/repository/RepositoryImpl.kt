package com.ands.wb5weekweb.repository

import android.content.Context
import com.ands.wb5weekweb.api.ApiServiceCats
import com.ands.wb5weekweb.model.tinder.Cat
import com.ands.wb5weekweb.model.tinder.LikedCats
import com.ands.wb5weekweb.model.tinder.VoteRequest
import com.ands.wb5weekweb.model.tinder.VoteResponse
import com.ands.wb5weekweb.utils.Constants
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import retrofit2.Response


class RepositoryImpl(
    context: Context,

    private val apiServiceCats: ApiServiceCats,
    private val client: HttpClient,

) : Repository {


    override suspend fun createVote(voteRequest: VoteRequest): Response<VoteResponse> {
        return apiServiceCats.createVote(body = voteRequest)
    }

    override suspend fun getCat(): Response<List<Cat>> {
        return apiServiceCats.getCat()
    }

    override suspend fun getLiked(subId: String): Response<List<LikedCats>> {
        return apiServiceCats.getLiked(subId = subId)
    }

    override suspend fun getCatKtor(): List<Cat> {
        return try {
            client.get(Constants.TINDER_KTOR_CATS)
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getLikedKtor(subId: String): List<LikedCats> {
        return try {
            client.get(Constants.TINDER_BASE_URL + "/v1/votes?sub_id=test123&&api_key=45b15940-98f7-4c78-a9aa-7283d14cc52e")
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }

}
