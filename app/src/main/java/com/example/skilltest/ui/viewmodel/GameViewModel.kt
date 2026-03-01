package com.example.skilltest.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skilltest.data.model.Card
import com.example.skilltest.data.model.Player
import com.example.skilltest.data.repository.DeckRepository
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {

    private val repository = DeckRepository()


    // cartas selecionadas na primeira tela
    var selectedCards = mutableStateListOf<Card>()
        private set

    // quantidade de jogadores
    var players by mutableIntStateOf(2)
        private set

    // cartas por jogador
    var cardsPerPlayer by mutableIntStateOf(2)
        private set

    // loading da API
    var isLoading by mutableStateOf(false)
        private set


    var playerHands = mutableStateListOf<Player>()
        private set

    var remainingCards = mutableStateListOf<Card>()
        private set



    fun toggleCard(card: Card) {
        val existingCard = selectedCards.find { it.code == card.code }

        if (existingCard != null) {
            selectedCards.remove(existingCard)
        } else {
            selectedCards.add(card)
        }
    }



    fun updatePlayers(value: Int) {
        if (value > 0) {
            players = value
        }
    }

    fun updateCardsPerPlayer(value: Int) {
        if (value > 0) {
            cardsPerPlayer = value
        }
    }



    fun startGame(onNavigate: () -> Unit) {
        viewModelScope.launch {

            isLoading = true

            try {
                val cardsString = selectedCards.joinToString(",") { it.code }

                // chamada da API
                repository.createDeck(cardsString)

                // embaralhar cartas selecionadas
                val shuffled = selectedCards.shuffled()

                val totalCardsNeeded = players * cardsPerPlayer

                // limpar estados antigos
                playerHands.clear()
                remainingCards.clear()

                var index = 0

                // distribuir cartas
                for (i in 1..players) {

                    val hand = shuffled
                        .drop(index)
                        .take(cardsPerPlayer)

                    playerHands.add(
                        Player(
                            id = i,
                            hand = hand
                        )
                    )

                    index += cardsPerPlayer
                }

                //  restantes
                remainingCards.addAll(shuffled.drop(index))

                isLoading = false

                onNavigate()

            } catch (e: Exception) {
                isLoading = false
                e.printStackTrace()
            }
        }
    }



    fun resetGame() {

        selectedCards.clear()

        players = 2
        cardsPerPlayer = 2


        playerHands.clear()
        remainingCards.clear()


        isLoading = false
    }
}