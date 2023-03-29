package com.lykkehjulet.screen.play.data

import com.lykkehjulet.screen.play.CharBoard

sealed class SelectedChar() {

    object NoChanges : SelectedChar()

    data class NewUpdate (var indexPosition : List<Int> , var updatedList : List<CharBoard>) : SelectedChar()

}
