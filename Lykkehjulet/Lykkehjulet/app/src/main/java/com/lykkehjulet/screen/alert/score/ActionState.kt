package com.lykkehjulet.screen.alert.score

sealed class ActionState {

    object OnLoading : ActionState()

    data class ScoreChanges(val from : Int , val to : Int  , val count : Int , var EachValue : Int ) : ActionState()

    data class OnBankrupt(val from : Int, val to : Int ) : ActionState()

    object OnLooseLife : ActionState()



}
