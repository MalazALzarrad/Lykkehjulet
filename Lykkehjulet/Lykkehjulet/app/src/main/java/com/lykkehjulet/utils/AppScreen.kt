package com.lykkehjulet.utils

sealed class AppScreen(val route : String) {

    object Welcome : AppScreen ("Welcome")

    object Play : AppScreen ("Play")

    object GameOver : AppScreen ("GameOver")

    object Settings : AppScreen ("Settings")
}
