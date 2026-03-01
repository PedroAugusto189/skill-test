package com.example.skilltest.data.remote

import com.example.skilltest.data.model.DeckResponse
import com.example.skilltest.data.model.DrawResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DeckApi {

    @GET("api/deck/new/shuffle/")
    suspend fun createDeck(
        @Query("cards") cards: String
    ): DeckResponse

    @GET("api/deck/{deck_id}/draw/")
    suspend fun drawCards(
        @Path("deck_id") deckId: String,
        @Query("count") count: Int
    ): DrawResponse
}