package com.example.mindand

import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil3.compose.AsyncImage

import com.example.mindand.ui.theme.MindAndTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MindAndTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.StartScreen.path) {
        composable(
            Screen.StartScreen.path,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(1000, easing = EaseIn)
                ) + fadeIn()
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(1000, easing = EaseOut)
                ) + fadeOut()
            }
        ) { StartScreen(navController) }

        composable(
            "profileScreen/{name}/{numOfColors}?imageUri={imageUri}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("numOfColors") { type = NavType.IntType },
                navArgument("imageUri") { type = NavType.StringType; defaultValue = "" }
            ),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(1000, easing = EaseIn)
                ) + fadeIn()
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(1000, easing = EaseOut)
                ) + fadeOut()
            }
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val imageUri = backStackEntry.arguments?.getString("imageUri")
            val numOfColors = backStackEntry.arguments?.getInt("numOfColors")
            ProfileScreen(navController, name, imageUri, numOfColors)
        }

        composable(
            "gameScreen/{numOfColors}",
            arguments = listOf(
                navArgument("numOfColors") { type = NavType.IntType }
            ),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(1000, easing = EaseIn)
                ) + fadeIn()
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(1000, easing = EaseOut)
                ) + fadeOut()
            }
        ) { backStackEntry ->
            val numOfColors = backStackEntry.arguments?.getInt("numOfColors") ?: 5
            GameScreen(navController = navController, numOfColors = numOfColors)
        }


        composable(
            "resultsScreen/{attemptsCount}/{isGameWon}/{numOfColors}",
            arguments = listOf(
                navArgument("attemptsCount") { type = NavType.IntType },
                navArgument("isGameWon") { type = NavType.BoolType },
                navArgument("numOfColors") { type = NavType.IntType }
            ),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(1000, easing = EaseIn)
                ) + fadeIn()
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(1000, easing = EaseOut)
                ) + fadeOut()
            }
        ) { backStackEntry ->
            val attemptsCount = backStackEntry.arguments?.getInt("attemptsCount") ?: 0
            val isGameWon = backStackEntry.arguments?.getBoolean("isGameWon") ?: false
            val numOfColors = backStackEntry.arguments?.getInt("numOfColors") ?: 5
            ResultsScreen(navController, attemptsCount, isGameWon, numOfColors)
        }

    }
}


@Preview
@Composable
fun ProfileScreenInitialPreview() {
    MindAndTheme {
        val navController = rememberNavController()
        ProfileScreen(navController = navController, name = "Dawid", imageUri = "", numOfColors = 5)
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MindAndTheme {
//        Greeting("Android")
//    }
//}