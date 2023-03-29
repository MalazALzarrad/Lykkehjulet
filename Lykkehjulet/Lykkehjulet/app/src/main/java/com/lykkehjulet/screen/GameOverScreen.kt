package com.lykkehjulet.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lykkehjulet.SpinViewModel
import com.lykkehjulet.screen.play.GameState
import com.lykkehjulet.ui.components.GeneralButtonStyle
import com.lykkehjulet.ui.theme.lostColor
import com.lykkehjulet.ui.theme.winColor


@Composable
fun GameOverScreen(viewmodel: SpinViewModel, playAgain: () -> Unit) {

    val gameState: GameState? by viewmodel.gameState.collectAsState()

   val userLife =  gameState?.userLife ?: 0
   val userScore =  gameState?.userScore ?: 0


    GameOverContent(userLife > 0, userScore ) {
        viewmodel.resetGame()
        playAgain()
    }


}


@Preview
@Composable
fun t() {
    GameOverContent(false, 100) {

    }
}


@Composable
fun GameOverContent(isWin: Boolean, Score: Int, playAgain: () -> Unit) {

    val background = if (isWin) {
        winColor
    } else {
        lostColor
    }




    Column(
        Modifier
            .fillMaxSize()
            .background(background)
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()

                .weight(1f)

            ,
            contentAlignment = Alignment.Center

        ) {

            if (isWin) {

                Column(modifier = Modifier.wrapContentHeight().wrapContentWidth()) {


                    Box(Modifier.fillMaxWidth().height(100.dp), contentAlignment = Alignment.Center) {
                        Text(
                            text = "You Won !",
                            style = MaterialTheme.typography.h2.copy(
                                color = Color.Black.copy(.6f)
                            )
                        )
                    }




                    Spacer(modifier = Modifier.size(24.dp))

                    Box(Modifier.fillMaxWidth().height(100.dp), contentAlignment = Alignment.Center) {
                        Text(
                            text = "$Score $",
                            style = MaterialTheme.typography.h3.copy(
                                color = Color.Black
                            )
                        )
                    }




                }


            } else {

                Box(Modifier.fillMaxWidth().height(100.dp), contentAlignment = Alignment.Center) {

                    Text(
                        text = "You Lost !",
                        style = MaterialTheme.typography.h3.copy(
                            color = Color.Black.copy(.6f)
                        )
                    )
                }

            }


        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)

            ,
            contentAlignment = Alignment.BottomCenter

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    Alignment.Center
                ) {
                    Text(
                        text = "Game Over",
                        style = MaterialTheme.typography.h4.copy(
                            color = Color.Black.copy(.6f)
                        )
                    )

                }

                Spacer(modifier = Modifier.size(48.dp))


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    GeneralButtonStyle(
                        FontColor = Color.White,
                        label = "Play Again"
                    ) {

                        playAgain()
                    }
                }


            }
        }

    }


}