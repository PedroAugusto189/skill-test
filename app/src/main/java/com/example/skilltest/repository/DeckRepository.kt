package com.example.skilltest.data.repository

import com.example.skilltest.data.model.Card
import com.example.skilltest.data.remote.RetrofitInstance

class DeckRepository {

    private val api = RetrofitInstance.api

    suspend fun createDeck(cards: String) =
        api.createDeck(cards)

    suspend fun drawCards(deckId: String, count: Int): List<Card> =
        api.drawCards(deckId, count).cards
}