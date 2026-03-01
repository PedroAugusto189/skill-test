package com.example.skilltest.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.skilltest.data.model.Card
import com.example.skilltest.ui.viewmodel.GameViewModel

@Composable
fun GameSetupScreen(
    viewModel: GameViewModel,
    allCards: List<Card>,
    onStartGame: () -> Unit
) {

    // ===============================
    // STATES DOS INPUTS
    // ===============================
    var playersText by remember { mutableStateOf(viewModel.players.toString()) }
    var cardsPerPlayerText by remember { mutableStateOf(viewModel.cardsPerPlayer.toString()) }

    val players = playersText.toIntOrNull() ?: 0
    val cardsPerPlayer = cardsPerPlayerText.toIntOrNull() ?: 0

    val totalNeeded = players * cardsPerPlayer

    val canStart =
        players > 0 &&
                cardsPerPlayer > 0 &&
                viewModel.selectedCards.size >= totalNeeded &&
                !viewModel.isLoading

    // ===============================
    // UI
    // ===============================
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Select Cards",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Selecionadas: ${viewModel.selectedCards.size} / Necessárias: $totalNeeded",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ===============================
        // GRID DE CARTAS
        // ===============================
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.weight(1f)
        ) {
            items(allCards) { card ->

                val isSelected = viewModel.selectedCards.any { it.code == card.code }

                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .background(Color.White)
                        .border(
                            width = 3.dp,
                            color = if (isSelected) Color.Green else Color.LightGray
                        )
                        .clickable {
                            viewModel.toggleCard(card)
                        }
                        .padding(12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = card.code)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // ===============================
        // INPUT: JOGADORES
        // ===============================
        OutlinedTextField(
            value = playersText,
            onValueChange = { playersText = it },
            label = { Text("number of players") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ===============================
        // INPUT: CARTAS POR JOGADOR
        // ===============================
        OutlinedTextField(
            value = cardsPerPlayerText,
            onValueChange = { cardsPerPlayerText = it },
            label = { Text("cards per player") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ===============================
        // BOTÃO START
        // ===============================
        Button(
            onClick = {
                viewModel.updatePlayers(players)
                viewModel.updateCardsPerPlayer(cardsPerPlayer)

                viewModel.startGame {
                    onStartGame()
                }
            },
            enabled = canStart,
            modifier = Modifier.fillMaxWidth()
        ) {

            if (viewModel.isLoading) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(20.dp)
                )
            } else {
                Text("Start Game")
            }
        }
    }
}