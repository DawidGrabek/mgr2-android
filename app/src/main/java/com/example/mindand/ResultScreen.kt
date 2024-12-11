package com.example.mindand

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mindand.db.entities.PlayerWithScore
import com.example.mindand.view_models.ResultsViewModel
import androidx.compose.foundation.lazy.items

@Composable
fun ResultsScreen(
    navController: NavHostController,
    attemptsCount: Int,
    isGameWon: Boolean,
    numOfColors: Int,
    viewModel: ResultsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val playersFlow = viewModel.loadPlayerScores()
    var playerScores = remember { mutableStateOf(emptyList<PlayerWithScore>()) }

    LaunchedEffect(playersFlow) {
        playersFlow.collect { newPlayers ->
            playerScores.value = newPlayers
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Wyświetlanie nagłówka wyniku
        Text(
            text = if (isGameWon) "Congratulations, You Won!" else "Game Over!",
            style = MaterialTheme.typography.displayMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Attempts: $attemptsCount",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Wyświetlanie historii wyników w tabelce
        Text("High Scores", style = MaterialTheme.typography.displayMedium)

        Spacer(modifier = Modifier.height(16.dp))

        ScoreHistory(playerScores = playerScores.value)

        Spacer(modifier = Modifier.height(32.dp))

        // Przyciski nawigacyjne
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { navController.popBackStack(Screen.StartScreen.path, inclusive = false) }) {
                Text("Home")
            }
            Button(onClick = {
                navController.navigate("gameScreen/$numOfColors")
            }) {
                Text("Play Again")
            }
        }
    }
}

@Composable
fun ScoreHistory(playerScores: List<PlayerWithScore>) {
    LazyColumn(
        modifier = Modifier
            .height(450.dp)
            .padding(16.dp)
    ) {
        items(playerScores) { playerScore ->
            ScoreHistoryRow(playerScore = playerScore)
            Divider(color = Color.Gray, thickness = 2.dp)
        }
    }
}

@Composable
fun ScoreHistoryRow(playerScore: PlayerWithScore) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = playerScore.playerName, style = MaterialTheme.typography.bodyLarge)
        Text(text = playerScore.score.toString(), style = MaterialTheme.typography.bodyLarge)
    }
}


