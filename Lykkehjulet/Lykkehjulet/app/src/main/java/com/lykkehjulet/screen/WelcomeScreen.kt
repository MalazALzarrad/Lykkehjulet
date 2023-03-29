package com.lykkehjulet.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lykkehjulet.R
import com.lykkehjulet.ui.components.ButtonStyle
import com.lykkehjulet.ui.components.gradientBackground
import com.lykkehjulet.ui.theme.BackgroundColorOne
import com.lykkehjulet.ui.theme.BackgroundColorTwo
import com.lykkehjulet.utils.gradiantColors


@Composable
fun Welcome( spin : (Boolean) -> Unit) {



    Column(modifier = Modifier
        .fillMaxSize()
        .gradientBackground(gradiantColors, 45f)) {

        Box(modifier = Modifier.fillMaxWidth().height(100.dp).padding(end = 32.dp) , contentAlignment = Alignment.CenterEnd) {

            IconButton(
                modifier = Modifier.size(48.dp),
                onClick = {
                    spin(false)

                }
            ) {
                Icon(
                    Icons.Filled.Settings,
                    "contentDescription",
                            modifier = Modifier.size(36.dp)
                )
            }
        }


        Box(modifier = Modifier.fillMaxWidth().weight(1f) , contentAlignment = Alignment.BottomCenter) {
            Image(painter = painterResource(id = R.mipmap.ic_spin_board), contentDescription = "Spin wheel" , modifier = Modifier.size(300.dp) )

        }

        Box(modifier = Modifier.fillMaxWidth().height(300.dp) , contentAlignment = Alignment.Center) {
            ButtonStyle(FontColor = Color.White, label = "Play") {
                spin(true)
            }
        }



    }


}