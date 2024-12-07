package com.example.mindand

sealed class Screen(val path: String) {
    object StartScreen : Screen("startScreen")
    class ProfileScreen(val name: String = "name",val imageUri: String = "imageUri"): Screen("profileScreen/{${name}}/{${imageUri}}") {
        fun generatePath() : String {
            return "profileScreen/${name}/${imageUri}"
        }
    }
    object GameScreen: Screen("gameScreen")
}