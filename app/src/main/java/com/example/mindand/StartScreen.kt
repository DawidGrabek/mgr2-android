package com.example.mindand

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import java.net.URLEncoder

@Composable
fun StartScreen(navController: NavHostController) {
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
            modifier = Modifier.padding(bottom = 48.dp)
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
                emailError.value = it.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()
            },
            label = "Enter Email",
            keyboardType = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = emailError.value,
            errorMessage = "Invalid email address",
            validate = { it.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextFieldWithError(
            value = numOfColors.value,
            onValueChange = {
                numOfColors.value = it
                numOfColorsError.value = it.toIntOrNull()?.let { num -> num !in 4..8 } ?: true
            },
            label = "Enter number of colors (4-8)",
            keyboardType = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = numOfColorsError.value,
            errorMessage = "Must be between 4 and 8",
            validate = { it.toIntOrNull()?.let { num -> num in 4..8 } ?: false }
        )

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = {
                val encodedUri = profileImageUri.value?.let { Uri.encode(it.toString()) }
                    ?: " "

                if (isNextButtonEnabled) {
                    navController.navigate("profileScreen/${name.value}/${encodedUri}/${numOfColors.value}")
                }
            },
            enabled = isNextButtonEnabled
        ) {
            Text("Next")
        }



    }
}
