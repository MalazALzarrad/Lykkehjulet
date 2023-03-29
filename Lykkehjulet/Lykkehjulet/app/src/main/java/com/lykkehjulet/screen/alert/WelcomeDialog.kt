package com.lykkehjulet.screen.alert

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.lykkehjulet.ui.components.GeneralButtonStyle
import com.lykkehjulet.ui.theme.BackgroundColorTwo


@Composable
fun WelcomeDialog(showDialogScreen: Boolean, onClose : () -> Unit) {

    var showDialog by remember { mutableStateOf(true) }

    if (showDialogScreen) {
        showDialog = false
    }


    if (showDialogScreen) {
        Dialog(
            onDismissRequest = { showDialog = false },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),

            ) {

            Card(
                modifier = Modifier
                    .widthIn(360.dp, 480.dp)
                    .heightIn(200.dp,240.dp)
                ,
                elevation = 8.dp,
                shape = RoundedCornerShape(12.dp),
                backgroundColor = BackgroundColorTwo

            ) {

                Column(modifier = Modifier
                    .fillMaxSize().padding(16.dp)
                ) {

                    Spacer(modifier = Modifier.size(8.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Welcome Spin Wheel",
                            style = MaterialTheme.typography.h4.copy(
                               color = MaterialTheme.colors.onPrimary ,
                                fontWeight = FontWeight.Black
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        text = "Playing this spin game",
                            style = MaterialTheme.typography.h6.copy(
                                color = Color.Black.copy(.8f)
                            )
                    )

                    Spacer(modifier = Modifier.size(8.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        val lifeText = buildAnnotatedString {
                            withStyle(style = SpanStyle(Color.Red, fontSize = 16.sp),) {
                                repeat(5) {
                                    append("❤️")
                                }
                            }
                        }

                        Text(
                            text = lifeText,
                            style = MaterialTheme.typography.subtitle1.copy(
                            )
                        )
                    }
                    Spacer(modifier = Modifier.size(24.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        GeneralButtonStyle(
                            FontColor = Color.White,
                            label = "Start"
                        ) {
                            onClose()
                        }
                    }


                    Spacer(modifier = Modifier.size(24.dp))

                }
            }
        }
    }



}