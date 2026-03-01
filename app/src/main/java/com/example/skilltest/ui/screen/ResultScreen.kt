package com.example.skilltest.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.skilltest.ui.viewmodel.GameViewModel
import com.example.skilltest.ui.components.CardItem

@Composable
fun ResultScreen(
    viewModel: GameViewModel,
    onPlayAgain: () -> Unit
) {

    val playerHands = viewModel.playerHands
    val remainingCards = viewModel.remainingCards

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        items(playerHands) { player ->

            Text(
                text = "Player ${player.id}",
                style = MaterialTheme.typography.titleLarge
            )

            LazyRow {
                items(player.hand) { card ->
                    CardItem(card)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }


        if (remainingCards.isNotEmpty()) {
            item {
                Text(
                    text = "Remaining Cards",
                    style = MaterialTheme.typography.titleLarge
                )

                LazyRow {
                    items(remainingCards) { card ->
                        CardItem(card)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.resetGame()
                    onPlayAgain()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Play Again")
            }
        }
    }
}