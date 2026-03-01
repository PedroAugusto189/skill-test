package com.example.skilltest

import com.example.skilltest.data.model.Card

fun generateFullDeck(): List<Card> {

    val suits = listOf("H", "D", "C", "S")
    val values = listOf(
        "A","2","3","4","5","6","7","8","9","10","J","Q","K"
    )

    val cards = mutableListOf<Card>()

    for (suit in suits) {
        for (value in values) {

            val code = "$value$suit"

            val suitName = when (suit) {
                "H" -> "HEARTS"
                "D" -> "DIAMONDS"
                "C" -> "CLUBS"
                "S" -> "SPADES"
                else -> ""
            }

            val valueName = when (value) {
                "A" -> "ACE"
                "J" -> "JACK"
                "Q" -> "QUEEN"
                "K" -> "KING"
                else -> value
            }

            cards.add(
                Card(
                    code = code,
                    image = "https://deckofcardsapi.com/static/img/$code.png",
                    suit = suitName,
                    value = valueName
                )
            )
        }
    }

    return cards
}