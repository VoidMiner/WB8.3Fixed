package com.ands.wb5weekweb.api

import com.ands.wb5weekweb.model.tinder.Cat
import com.ands.wb5weekweb.model.tinder.LikedCats
import com.ands.wb5weekweb.model.tinder.VoteRequest
import com.ands.wb5weekweb.model.tinder.VoteResponse
import com.ands.wb5weekweb.utils.Constants.Companion.TINDER_HEADER_API_KEY
import retrofit2.Response
import retrofit2.http.*


interface ApiServiceCats {

    @Headers(TINDER_HEADER_API_KEY)
    @POST("v1/votes")
    suspend fun createVote(@Body body: VoteRequest): Response<VoteResponse>

    @Headers(TINDER_HEADER_API_KEY)
    @GET("v1/images/search")
    suspend fun getCat(): Response<List<Cat>>

    @Headers(TINDER_HEADER_API_KEY)
    @GET("v1/votes")
    suspend fun getLiked(@Query("sub_id") subId: String): Response<List<LikedCats>>



}