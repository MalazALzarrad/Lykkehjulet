package com.lykkehjulet.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lykkehjulet.ui.theme.GradiantYellowOne
import com.lykkehjulet.ui.theme.GradiantYellowTwo

@Composable
fun GeneralButtonStyle(FontColor: Color, label: String, onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .width(200.dp)
            .height(48.dp)
            .padding(start = 16.dp, end = 16.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(GradiantYellowOne, GradiantYellowTwo)
                ),
                shape = RoundedCornerShape(24.dp)

            )
            .clip(RoundedCornerShape(24.dp))
            .clickable {
                onClick()
            }


    ) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = label,
                color = FontColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,

                style = MaterialTheme.typography.h6.copy(
                    color = MaterialTheme.colors.surface,

                    )

            )
        }
    }


}



@Composable
fun ButtonStyle(FontColor: Color, label: String, onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .width(200.dp)
            .height(60.dp)
            .padding(start = 16.dp, end = 16.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(GradiantYellowOne, GradiantYellowTwo)
                ),
                shape = RoundedCornerShape(24.dp)

            )
            .clip(RoundedCornerShape(24.dp))
            .clickable {
                onClick()
            }


    ) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = label,
                color = FontColor,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,

                style = MaterialTheme.typography.h6.copy(
                    color = MaterialTheme.colors.surface,

                    )

            )
        }
    }


}

@Composable
fun TextWithShadow(
    text: String,
    modifier: Modifier
) {
    Text(
        text = text,
        color = Color.DarkGray,
        modifier = modifier
            .offset(
                x = 2.dp,
                y = 2.dp
            )
            .alpha(0.75f)
    )
    Text(
        text = text,
        color = Color.White,
        modifier = modifier
    )
}
