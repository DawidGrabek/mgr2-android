package com.example.mindand

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun ProfileScreen(navController: NavHostController, name: String, imageUri: String?, numOfColors: Int?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Player: $name", style = MaterialTheme.typography.displayMedium)

        Spacer(modifier = Modifier.height(16.dp))

        if (imageUri.isNullOrEmpty()) {
            Image(
                painter = painterResource(R.drawable.ic_baseline_question_mark_24),
                contentDescription = "Default profile photo",
                modifier = Modifier.size(100.dp)
            )
        } else {
            AsyncImage(
                model = imageUri,
                contentDescription = "Profile photo",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { navController.popBackStack() }) {
                Text("Back")
            }
            Button(onClick = {
                navController.navigate("gameScreen/$numOfColors")
            }) {
                Text("Play")
            }
        }
    }
}

