package com.example.mindand

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

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
        IconButton(
            onClick = onCheckClick,
            enabled = isCheckEnabled,
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

        // Wyświetlanie kółek informacyjnych (feedback) w układzie 2x2
        FeedbackCircles(feedbackColors)
    }
}

// Funkcja FeedbackCircles w układzie 2x2
@Composable
fun FeedbackCircles(feedbackColors: List<Color>) {
    Column {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            SmallCircle(feedbackColors[0])
            SmallCircle(feedbackColors[1])
        }
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            SmallCircle(feedbackColors[2])
            SmallCircle(feedbackColors[3])
        }
    }
}

// Funkcja CircularButton (przycisk wyboru koloru)
@Composable
fun CircularButton(
    color: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(50.dp)
            .background(MaterialTheme.colorScheme.background),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        shape = CircleShape
    ) {}
}

// Funkcja SmallCircle (kółko informacyjne)
@Composable
fun SmallCircle(color: Color) {
    Box(
        modifier = Modifier
            .size(20.dp)
            .clip(CircleShape)
            .background(color)
            .border(2.dp, MaterialTheme.colorScheme.outline, CircleShape)
    )
}
