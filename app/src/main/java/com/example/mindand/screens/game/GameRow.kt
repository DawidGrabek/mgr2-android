package com.example.mindand.screens.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mindand.R

@Composable
fun GameRow(
    selectedColors: List<Color>,
    onSelectColorClick: (Int) -> Unit,
    onCheckClick: () -> Unit,
    feedbackColors: List<Color>,
    isCheckEnabled: Boolean
) {
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Wyświetlanie przycisków wyboru kolorów
        selectedColors.forEachIndexed { index, color ->
            CircularButton(color = color) {
                onSelectColorClick(index)
            }
        }

        // Przycisk sprawdzający próbę
        AnimatedVisibility(visible = isCheckEnabled) {
            IconButton(
                onClick = onCheckClick,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_check_24),
                    contentDescription = "Check"
                )
            }
        }

        // Wyświetlanie kółek informacyjnych (feedback) w układzie 2x2
        FeedbackCircles(feedbackColors)
    }
}

// Funkcja FeedbackCircles w układzie 2x2
@Composable
fun FeedbackCircles(feedbackColors: List<Color>) {
    // Przechowujemy animowane kolory kółek
    val animatedColors = remember { mutableStateListOf(Color.Gray, Color.Gray, Color.Gray, Color.Gray) }

    // Animujemy każde kółko po kolei z opóźnieniem
    LaunchedEffect(feedbackColors) {
        feedbackColors.forEachIndexed { index, color ->
            kotlinx.coroutines.delay(index * 200L) // 200 ms opóźnienia dla każdego kółka
            animatedColors[index] = color // Zmiana koloru kółka
        }
    }

    Column {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            animatedColors.subList(0, 2).forEach { color ->
                SmallCircle(color)
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            animatedColors.subList(2, 4).forEach { color ->
                SmallCircle(color)
            }
        }
    }
}

// Funkcja CircularButton (przycisk wyboru koloru)
@Composable
fun CircularButton(
    color: Color,
    onClick: () -> Unit
) {
    val animatedColor by animateColorAsState(
        targetValue = color,
        animationSpec = tween(durationMillis = 500, easing = EaseInOut)
    )

    Button(
        onClick = onClick,
        modifier = Modifier
            .size(50.dp)
            .background(MaterialTheme.colorScheme.background),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
        colors = ButtonDefaults.buttonColors(
            containerColor = animatedColor,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        shape = CircleShape
    ) {}
}


// Funkcja SmallCircle (kółko informacyjne)
@Composable
fun SmallCircle(color: Color) {
    val animatedColor by animateColorAsState(
        targetValue = color,
        animationSpec = tween(durationMillis = 300)
    )

    Box(
        modifier = Modifier
            .size(20.dp)
            .clip(CircleShape)
            .background(animatedColor)
            .border(2.dp, MaterialTheme.colorScheme.outline, CircleShape)
    )
}

