package com.lykkehjulet.screen.alert

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lykkehjulet.utils.SpinScore


@Composable
fun SpinWheel(value: SpinScore?) {


    val available = value != null

    val isBankrupt = if (value != null) {

        value is SpinScore.Bankrupt

    } else false

    val backgroundColor = if (available) {

        if (isBankrupt) Color.Black else Color.White

    } else Color.LightGray

    val contentColor = if (available) {

        if (isBankrupt) Color.White else Color.Black

    } else Color.Gray


    Box(
        Modifier
            .width(200.dp)
            .height(60.dp)
            .background(backgroundColor),
        contentAlignment = Alignment.Center


    ) {

        if (available) {

            if (value is SpinScore.Score) {
                ScoreText(label = "${value.value} $" ,contentColor)
            } else {
                ScoreText(label = " Bankrupt",contentColor)
            }


        } else {

            ScoreText(label = "  ???  ",contentColor)
        }


    }


}


@Composable
fun ScoreText(label : String , color : Color) {
    Text(
        text = label,
        style = MaterialTheme.typography.h6.copy(
            color = color,

            )

    )
}

