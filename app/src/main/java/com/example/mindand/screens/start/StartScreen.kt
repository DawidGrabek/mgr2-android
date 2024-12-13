package com.example.mindand.screens.start

import android.net.Uri
import android.util.Patterns
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mindand.providers.AppViewModelProvider
import com.example.mindand.view_models.ProfileViewModel
import kotlinx.coroutines.launch

@Composable
fun StartScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = viewModel(factory = AppViewModelProvider.Factory)
    ) {
    val coroutineScope = rememberCoroutineScope()

    val name = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }
    val numOfColors = rememberSaveable { mutableStateOf("") }
    val profileImageUri = rememberSaveable { mutableStateOf<Uri?>(null) }

    val nameError = remember { mutableStateOf(false) }
    val emailError = remember { mutableStateOf(false) }
    val numOfColorsError = remember { mutableStateOf(false) }

    // Walidacja formularza
    val isFormValid = !nameError.value && !emailError.value && !numOfColorsError.value
    val isNextButtonEnabled = isFormValid && name.value.isNotEmpty() && email.value.isNotEmpty() && numOfColors.value.isNotEmpty()

    // Animacja
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteScale")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            tween(1000),
            RepeatMode.Reverse
        ), label = "infiniteScale"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "MasterAnd",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = TransformOrigin.Center
                }
        )

        // Wyświetlanie zdjęcia, opcjonalne
        ProfileImageWithPicker(profileImageUri.value) { uri ->
            profileImageUri.value = uri
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextFieldWithError(
            value = name.value,
            onValueChange = {
                name.value = it
                nameError.value = it.isEmpty() // Walidacja w czasie rzeczywistym
            },
            label = "Enter Name",
            keyboardType = KeyboardOptions(keyboardType = KeyboardType.Text),
            isError = nameError.value,
            errorMessage = "Name cannot be empty",
            validate = { it.isNotEmpty() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextFieldWithError(
            value = email.value,
            onValueChange = {
                email.value = it
                emailError.value = it.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(it).matches()
            },
            label = "Enter Email",
            keyboardType = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = emailError.value,
            errorMessage = "Invalid email address",
            validate = { it.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(it).matches() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextFieldWithError(
            value = numOfColors.value,
            onValueChange = {
                numOfColors.value = it
                numOfColorsError.value = it.toIntOrNull()?.let { num -> num !in 5..10 } ?: true
            },
            label = "Enter number of colors (5-10)",
            keyboardType = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = numOfColorsError.value,
            errorMessage = "Must be between 5 and 10",
            validate = { it.toIntOrNull()?.let { num -> num in 5..10 } ?: false }
        )

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = {
                viewModel.name.value = name.value
                viewModel.email.value = email.value
                coroutineScope.launch {
                    viewModel.savePlayer()
                }

                numOfColors.value
                val encodedUri = profileImageUri.value?.let { Uri.encode(it.toString()) }
                navController.navigate(
                    "profileScreen/${name.value}/${numOfColors.value}" +
                            if (encodedUri != null) "?imageUri=$encodedUri" else ""
                )
            },
            enabled = isNextButtonEnabled
        ) {
            Text("Next")
        }
    }
}

