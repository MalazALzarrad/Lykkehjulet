package com.lykkehjulet.screen.play


data class GameState(
    var userScore : Int = 0,
    var userLife : Int = 5,
    var category : String,
    var found : Int
)



// Category

data class CharState(
    val char: Char,
    val isVisible : Boolean = true
)


data class CharBoard (
    var index : Int,
    val char: Char ,
    val isChar : Boolean = false,
    val isVisible: Boolean = false
    )


// Alert

sealed class Alerts {
    object WelcomeAlert : Alerts()
    object SpinAlert : Alerts()
    object ScoreAlert : Alerts()
    object ExitAlert : Alerts()
}