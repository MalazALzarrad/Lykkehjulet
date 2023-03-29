package com.lykkehjulet.screen.alert.score

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*

import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@Preview
@Composable
fun LooseLife() {


    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            Modifier
                .fillMaxWidth()
                .height(150.dp), contentAlignment = Alignment.Center) {
            Text(
                text = "❤️",
                style = MaterialTheme.typography.h1.copy(
                    color = Color.Red
                )
            )
        }
        Box(
            Modifier
                .fillMaxWidth()
                .height(150.dp), contentAlignment = Alignment.Center) {
            Text(
                text = "You have lost one Life !",
                style = MaterialTheme.typography.h5.copy(
                    color = Color.Black.copy(.6f)
                )
            )
        }
    }




}

@Composable
fun AddScore(fromAmount: Int, toAmount: Int , count : Int , Each : Int ) {


    var amounts by remember { mutableStateOf(fromAmount) }
    val ampuntsCounter by animateIntAsState(
        targetValue = amounts,
        animationSpec = tween(
            durationMillis = 2000,
            easing = FastOutSlowInEasing
        )
    )

    LaunchedEffect(key1 = Unit) {

        delay(300)
        // view
        amounts = toAmount
    }

    val Score = buildAnnotatedString {
        withStyle(style = SpanStyle(Color.Black, fontSize = 18.sp),) {
            append(" $count ")
        }
        withStyle(style = SpanStyle(Color.LightGray, fontSize = 14.sp),) {
            append("x ")
        }
        withStyle(style = SpanStyle(Color.Black, fontSize = 18.sp),) {
            append("${Each} $ ")
        }
        withStyle(style = SpanStyle(Color.Black, fontSize = 14.sp),) {
            append("= ")
        }
        withStyle(style = SpanStyle(Color.Black, fontSize = 18.sp),) {
            append("${count*Each} $ ")
        }
    }


    Column(modifier = Modifier.fillMaxSize()) {

        Box(
            Modifier
                .fillMaxWidth()
                .height(150.dp)
                .weight(1f)

            , contentAlignment = Alignment.Center) {
            Text(
                text = "$ampuntsCounter $",
                style = MaterialTheme.typography.h4.copy(
                    color = Color.White.copy(.6f)
                )
            )
        }

        Box(
            Modifier
                .fillMaxWidth()
                .height(150.dp)
                .weight(1f)
            , contentAlignment = Alignment.Center) {
            Text(
                text = Score,
                style = MaterialTheme.typography.h5.copy(
                    color = Color.White.copy(.6f)
                )
            )
        }


    }




}




@Preview
@Composable
fun preview() {

    BankruptScore(3000, 500)

}


@Composable
fun BankruptScore(fromAmount: Int, toAmount: Int) {


    var amounts by remember { mutableStateOf(fromAmount) }
    val ampuntsCounter by animateIntAsState(
        targetValue = amounts,
        animationSpec = tween(
            durationMillis = 2000,
            easing = FastOutSlowInEasing
        )
    )

    LaunchedEffect(key1 = Unit) {

        delay(300)
        // view
        amounts = toAmount
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        Alignment.Center
    ) {
        Card(
            border = BorderStroke(1.dp, Color.Red),
            modifier = Modifier.size(300.dp),
            backgroundColor = Color.Black
        ) {


            Column(modifier = Modifier.fillMaxSize()) {

                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .weight(1f)

                    , contentAlignment = Alignment.Center) {
                    Text(
                        text = " Bankrupt \uD83D\uDCB0",
                        style = MaterialTheme.typography.h4.copy(
                            color = Color.White.copy(.6f)
                        )
                    )
                }

                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .weight(1f)
                    , contentAlignment = Alignment.Center) {
                    Text(
                        text = "$ampuntsCounter $",
                        style = MaterialTheme.typography.h5.copy(
                            color = Color.White.copy(.6f)
                        )
                    )
                }


            }






        }
    }


}