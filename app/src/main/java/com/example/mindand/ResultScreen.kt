package com.example.mindand

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ResultsScreen(
    navController: NavHostController,
    attemptsCount: Int,
    isGameWon: Boolean,
    numOfColors: Int
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

