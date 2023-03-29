package com.lykkehjulet.utils

sealed class SpinScore() {

    data class Score(var value : Int) : SpinScore()

    object Bankrupt : SpinScore()
}
