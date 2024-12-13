package com.example.mindand

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mindand.screens.game.GameScreen
import com.example.mindand.screens.profile.ProfileScreen
import com.example.mindand.screens.result.ResultsScreen
import com.example.mindand.screens.start.StartScreen

import com.example.mindand.ui.theme.MindAndTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

    NavHost(navController = navController, startDestination = "StartScreen") {
        composable(
            "StartScreen",
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