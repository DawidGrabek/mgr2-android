package com.example.mindand

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun GameScreen(navController: NavHostController, numOfColors: Int) {
    val baseColors = listOf(Color.Red, Color.Blue, Color.Green, Color.Magenta, Color.Yellow)
    val availableColors by remember {
        mutableStateOf(baseColors + List(numOfColors - baseColors.size) {
            Color(
                red = Random.nextFloat(),
                green = Random.nextFloat(),
                blue = Random.nextFloat()
            )
        })
    }

    var attempts by remember { mutableStateOf(mutableListOf<Pair<List<Color>, List<Color>>>()) }
    var selectedColors by remember { mutableStateOf(List(4) { Color.White }) }
    var feedbackColors by remember { mutableStateOf(List(4) { Color.Gray }) }
    val isCheckEnabled = selectedColors.none { it == Color.White }

    val correctColors by remember { mutableStateOf(selectRandomColors(availableColors)) }
    var isGameFinished by remember { mutableStateOf(false) }
    var isResetting by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Your score: ${attempts.size}", style = MaterialTheme.typography.displayLarge)
        Spacer(modifier = Modifier.height(16.dp))

        // Wyświetlanie wszystkich zakończonych prób
        attempts.forEach { (selected, feedback) ->
            GameRow(
                selectedColors = selected,
                feedbackColors = feedback,
                isCheckEnabled = false,
                onSelectColorClick = {},
                onCheckClick = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Wyświetlanie aktualnej próby
        if (!isGameFinished && !isResetting) {
            GameRow(
                selectedColors = selectedColors,
                feedbackColors = feedbackColors,
                isCheckEnabled = isCheckEnabled,
                onSelectColorClick = { index ->
                    selectedColors = selectNextAvailableColor(
                        availableColors,
                        selectedColors,
                        index
                    )
                },
                onCheckClick = {
                    feedbackColors = checkColors(selectedColors, correctColors, Color.Gray)

                    if (feedbackColors.all { it == Color.Red }) {
                        isGameFinished = true
                        attempts.add(Pair(selectedColors, feedbackColors))
                    } else {
                        attempts.add(Pair(selectedColors, feedbackColors))
                        isResetting = true
                    }
                }
            )
        }

        // Reset stanu po opóźnieniu
        if (isResetting) {
            LaunchedEffect(Unit) {
                kotlinx.coroutines.delay(200) // Opóźnienie 200 ms
                selectedColors = List(4) { Color.White }
                feedbackColors = List(4) { Color.Gray }
                isResetting = false
            }
        }

        if (isGameFinished) {
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                navController.navigate(
                    "resultsScreen/${attempts.size}/${feedbackColors.all { it == Color.Red }}/$numOfColors"
                )
            }) {
                Text("Finish Game")
            }
        }
    }
}

// Funkcja uniemożliwiająca wybranie tego samego koloru
fun selectNextAvailableColor(
    availableColors: List<Color>,
    selectedColors: List<Color>,
    buttonIndex: Int
): List<Color> {
    val newColors = selectedColors.toMutableList()
    var nextColorIndex = (availableColors.indexOf(selectedColors[buttonIndex]) + 1) % availableColors.size

    while (availableColors[nextColorIndex] in selectedColors) {
        nextColorIndex = (nextColorIndex + 1) % availableColors.size
    }
    newColors[buttonIndex] = availableColors[nextColorIndex]
    return newColors
}

// Funkcja: Wybierz losowe 4 unikalne kolory
fun selectRandomColors(availableColors: List<Color>): List<Color> {
    return availableColors.shuffled(Random.Default).take(4)
}


// Funkcja: Porównaj kolory i zwróć informację zwrotną
fun checkColors(selectedColors: List<Color>, correctColors: List<Color>, colorNotFound: Color): List<Color> {
    return selectedColors.mapIndexed { index, color ->
        when {
            color == correctColors[index] -> Color.Red // Prawidłowy kolor na prawidłowej pozycji
            color in correctColors -> Color.Yellow // Prawidłowy kolor, ale na złej pozycji
            else -> colorNotFound // Koloru nie ma w wylosowanym zestawie
        }
    }
}
