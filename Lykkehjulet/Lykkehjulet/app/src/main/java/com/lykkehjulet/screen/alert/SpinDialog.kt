package com.lykkehjulet.screen.alert

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import com.lykkehjulet.R
import com.lykkehjulet.ui.components.ButtonStyle
import com.lykkehjulet.ui.theme.BackgroundColorTwo
import com.lykkehjulet.utils.Score
import com.lykkehjulet.utils.SpinScore
import kotlinx.coroutines.delay


@Composable
fun SpinDialog(showDialogScreen: Boolean, onClose: (SpinScore) -> Unit) {

    var showDialog by remember { mutableStateOf(true) }

    if (showDialogScreen) {
        showDialog = false
    }

    var triggerSpin by remember { mutableStateOf(false) }
    var value: SpinScore? by remember { mutableStateOf(null) }

    LaunchedEffect(key1 = triggerSpin) {
        if (triggerSpin) {
            delay(3000)
            value = Score().generateScore().random()
            delay(2000)
            onClose(value!!)
        }
    }






    if (showDialogScreen) {
        Dialog(
            onDismissRequest = { showDialog = false },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),

            ) {

            Card(
                modifier = Modifier
                    .widthIn(360.dp, 480.dp)
                    .heightIn(200.dp, 240.dp),
                elevation = 8.dp,
                shape = RoundedCornerShape(12.dp),
                backgroundColor = BackgroundColorTwo

            ) {

                ConstraintLayout(modifier = Modifier.fillMaxSize()) {

                    val (icon, SpinScore, button) = createRefs()

                    Image(painter = painterResource(id = R.mipmap.ic_spin_board), contentDescription = "Spin wheel", modifier = Modifier
                        .size(100.dp)
                        .alpha(.2f)
                        .constrainAs(icon) {
                            top.linkTo(parent.top, (8).dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        })

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .constrainAs(SpinScore) {
                            top.linkTo(parent.top, 100.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }, contentAlignment = Alignment.Center) {
                        SpinWheel(value)
                    }

                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .constrainAs(button) {
                            bottom.linkTo(parent.bottom, 16.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }, contentAlignment = Alignment.Center) {
                        ButtonStyle(FontColor = Color.White, label = "Spin") {
                            triggerSpin = true
                        }
                    }


                }


            }
        }
    }


}