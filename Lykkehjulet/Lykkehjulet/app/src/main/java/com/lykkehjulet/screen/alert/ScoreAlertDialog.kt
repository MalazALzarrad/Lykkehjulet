package com.lykkehjulet.screen.alert

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.lykkehjulet.screen.alert.score.LooseLife
import com.lykkehjulet.screen.alert.score.BankruptScore
import com.lykkehjulet.screen.alert.score.ActionState
import com.lykkehjulet.screen.alert.score.AddScore
import com.lykkehjulet.ui.theme.BackgroundColorTwo
import com.lykkehjulet.ui.theme.lostLifeBackground
import kotlinx.coroutines.delay


@Composable
fun ScoreAlertDialog(showDialogScreen: Boolean, actionState : ActionState, onClose : (ActionState) -> Unit) {

    var showDialog by remember { mutableStateOf(true) }

    if (showDialogScreen) {
        showDialog = false
    }

    LaunchedEffect(key1 = actionState) {
        delay(3000)
        onClose(actionState)
    }

    val backgroundColor = when (actionState) {
        is ActionState.OnBankrupt -> Color.Black
        ActionState.OnLoading -> Color.LightGray
        ActionState.OnLooseLife -> lostLifeBackground
        is ActionState.ScoreChanges -> BackgroundColorTwo
    }

    if (showDialogScreen) {
        Dialog(
            onDismissRequest = { showDialog = false },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),

            ) {

            Card(
                modifier = Modifier
                    .widthIn(360.dp, 480.dp)
                    .heightIn(300.dp, 340.dp),
                elevation = 8.dp,
                shape = RoundedCornerShape(12.dp),
                backgroundColor = backgroundColor

            ) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center

                ) {

                    when (actionState) {
                        is ActionState.OnBankrupt -> {
                            val bankrupt = (actionState as ActionState.OnBankrupt)

                            BankruptScore(bankrupt.from, bankrupt.to)
                        }
                        ActionState.OnLoading -> {

                        }
                        ActionState.OnLooseLife -> {
                            LooseLife()
                        }
                        is ActionState.ScoreChanges -> {

                            val score = (actionState as ActionState.ScoreChanges)

                            AddScore(score.from, score.to , score.count , score.EachValue)

                        }
                    }

                }

            }

        }

    }
}