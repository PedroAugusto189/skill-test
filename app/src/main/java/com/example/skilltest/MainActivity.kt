package com.example.skilltest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skilltest.ui.screen.GameSetupScreen
import com.example.skilltest.ui.screen.ResultScreen
import com.example.skilltest.ui.theme.SkillTestTheme
import com.example.skilltest.ui.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SkillTestTheme {

                val navController = rememberNavController()
                val viewModel: GameViewModel = viewModel()
                val allCards = generateFullDeck()

                NavHost(
                    navController = navController,
                    startDestination = "setup"
                ) {

                    // 🔹 Tela de configuração
                    composable("setup") {
                        GameSetupScreen(
                            viewModel = viewModel,
                            allCards = allCards,
                            onStartGame = {
                                viewModel.startGame {
                                    navController.navigate("result")
                                }
                            }
                        )
                    }

                    // 🔹 Tela de resultado (com Play Again)
                    composable("result") {
                        ResultScreen(
                            viewModel = viewModel,
                            onPlayAgain = {
                                navController.popBackStack() // volta para setup
                            }
                        )
                    }
                }
            }
        }
    }
}