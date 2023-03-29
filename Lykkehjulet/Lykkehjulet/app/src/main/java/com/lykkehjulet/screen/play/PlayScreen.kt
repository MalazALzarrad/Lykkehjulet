package com.lykkehjulet.screen.play

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.lykkehjulet.SpinViewModel
import com.lykkehjulet.screen.alert.score.ActionState
import com.lykkehjulet.screen.alert.ScoreAlertDialog
import com.lykkehjulet.screen.alert.SpinDialog
import com.lykkehjulet.screen.alert.WelcomeDialog
import com.lykkehjulet.ui.components.gradientBackground
import com.lykkehjulet.ui.theme.ScoreColor
import com.lykkehjulet.utils.SpinScore
import com.lykkehjulet.utils.gradiantColors
import kotlinx.coroutines.delay


@Composable
fun PlayScreen(viewModel: SpinViewModel, navGameOver: () -> Unit) {

    val charlist: List<Char> by remember { mutableStateOf<List<Char>>(('A'..'Z').toList()) }



    var alerts by remember {
        mutableStateOf<Alerts?>(null)
    }

    var result by remember {
        mutableStateOf<Any?>(null)
    }

    val (isAlertActive,onAlertChange) = remember {
        mutableStateOf(false)
    }

    // char onClick
    val onSelection : (Char) -> Unit = {
        viewModel.onCharClick(it)
    }

    // Generate Character

    val onUserAction by viewModel.userSelectionState.collectAsState()

    LaunchedEffect(key1 = onUserAction) {

        if (onUserAction) {
            delay(1000)
            alerts = Alerts.ScoreAlert
            onAlertChange(true)
           // nav(UserAction.Action)
        }

    }

    val charBoard by viewModel.charBoardState.collectAsState()
    val gameState by viewModel.gameState.collectAsState()

    //
    LaunchedEffect(key1 = Unit) {

        viewModel.startGame()
        delay(300)
        if (alerts == null) {
            alerts = Alerts.WelcomeAlert
            onAlertChange(true)
        }
    }

    LaunchedEffect(key1 = alerts , key2 = isAlertActive) {

        if (alerts == null) return@LaunchedEffect

        when (alerts) {
            Alerts.ExitAlert -> {

            }
            Alerts.ScoreAlert -> {

                if (!isAlertActive) {
                    val actionState = result as ActionState
                   val userLife =  viewModel.applyChanges(actionState)

                    if (userLife > 0) {

                        // Check where we Win

                        if (viewModel.isWon()) {
                            navGameOver()
                        } else {
                            alerts = Alerts.SpinAlert
                            onAlertChange(true)
                        }



                    } else {
                        navGameOver()
                    }

                }
            }
            Alerts.SpinAlert -> {

                if (!isAlertActive) {
                    viewModel.tempScore = result as SpinScore
                }

            }
            Alerts.WelcomeAlert -> {
                if (!isAlertActive) {
                    delay(1000)
                    alerts = Alerts.SpinAlert
                    onAlertChange(true)
                }
            }
            null -> {

            }
        }


    }


    Box(modifier = Modifier
        .fillMaxSize()
        .gradientBackground(gradiantColors, 45f)
    ) {


        Column {

            GamePoints(gameState)

            DisplayBoardMain(charBoard , viewModel.CharBoardNewChangesIndex)

            DisplayOptionsMain(charlist, onSelection)

        }


    }


    // Alert Dialog

    if (alerts != null && isAlertActive) {


        when (alerts) {
            Alerts.ExitAlert -> {
                WelcomeDialog(isAlertActive) {
                    onAlertChange(false)
                }
            }
            Alerts.ScoreAlert -> {

                ScoreAlertDialog(isAlertActive , actionState = viewModel.doAction()) {
                    result = it
                    onAlertChange(false)
                }


            }
            Alerts.SpinAlert -> {
                SpinDialog(isAlertActive) {

                    if ((it is SpinScore.Bankrupt)) {
                        onAlertChange(false)
                        viewModel.tempScore = it
                        alerts = Alerts.ScoreAlert
                        onAlertChange(true)
                    } else {
                        result = it
                        onAlertChange(false)
                    }


                }

            }
            Alerts.WelcomeAlert -> {
                WelcomeDialog(isAlertActive) {
                    onAlertChange(false)
                }
            }
            null -> TODO()
        }





    }








}


@Composable
fun GamePoints(gameState: GameState?) {


    val lifeText = buildAnnotatedString {

        withStyle(style = SpanStyle(Color.White )) {
            append("Life : ")
        }

        withStyle(style = SpanStyle(Color.Red  )) {

            var userLife = gameState?.userLife ?: 0

            repeat(userLife) {
                append("❤️")
            }
        }
    }

    val ScoreText = buildAnnotatedString {

        withStyle(style = SpanStyle(Color.White )) {
            append("Score : ")
        }

        withStyle(style = SpanStyle(ScoreColor)) {

            var userScore = gameState?.userScore ?: 0

            append(userScore.toString())
        }
    }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(8.dp)
    ) {

        Box(
            Modifier
                .fillMaxSize()
                .weight(.4f)

            ,
            contentAlignment = Alignment.CenterStart


        ) {




                Text(
                    text = ScoreText,
                    style = MaterialTheme.typography.h6.copy(
                        color = Color.Black,

                        ),
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )




        }

        Box(
            Modifier
                .fillMaxSize()
                .weight(.6f)
            ,
            contentAlignment = Alignment.CenterEnd


        ) {




                Text(
                    text = lifeText,
                    style = MaterialTheme.typography.h6.copy(


                    ),
                    maxLines = 1,
                    modifier = Modifier.fillMaxWidth()
                )




        }
    }


}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DisplayOptionsMain(char: List<Char> ,onSelection : (Char) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(16.dp)
    ) {
        Text(
            text = "Choose : ",
            style = MaterialTheme.typography.h6.copy(
                color = Color.White


            ),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )


        Box(modifier = Modifier
            .background(Color.LightGray.copy(alpha = .2f))
            .clip(RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .weight(1f) , ) {

            // on below line we are adding lazy
            // vertical grid for creating a grid view.
            LazyVerticalGrid(
                // on below line we are setting the
                // column count for our grid view.
                cells = GridCells.Fixed(8),
                // on below line we are adding padding
                // from all sides to our grid view.
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()

            ) {
                // on below line we are displaying our
                // items upto the size of the list.
                items(char.size) {
                    val selected = char[it]
                    DisplayOptions(selected) {
                        onSelection(selected)
                    }

                }
            }
        }

    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DisplayBoardMain(charBoard: List<CharBoard>, charBoardNewChangesIndex: List<Int>?) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(16.dp)

    ) {


        Text(
            text = "Board : ",
            style = MaterialTheme.typography.h6.copy(
                color = Color.White


            ),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )


        Box(
            modifier = Modifier
                .background(Color.LightGray.copy(alpha = .2f))
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
            ,
        ) {

            // on below line we are adding lazy
            // vertical grid for creating a grid view.
            LazyVerticalGrid(
                // on below line we are setting the
                // column count for our grid view.
                cells = GridCells.Fixed(10),
                // on below line we are adding padding
                // from all sides to our grid view.
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                // on below line we are displaying our
                // items upto the size of the list.
                items(charBoard.size) {
                    val selected = charBoard[it]


                    val highligh = if (charBoardNewChangesIndex != null) {
                        it in charBoardNewChangesIndex
                    } else false

                    DisplayBoard(selected , highligh)

                }
            }
        }
    }


}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DisplayOptions(char: Char , onClick : () -> Unit ) {

    Surface(
        modifier = Modifier
            .size(40.dp),
        color = Color.White,
        contentColor = Color.Black,
        elevation = 1.dp,
        shape = RoundedCornerShape(4.dp),
        onClick = onClick
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                char.toString(),

                style = MaterialTheme.typography.h4.copy(
                    color = Color.Black,
                    fontWeight = FontWeight.Black
                    )
            )

        }
    }


}


@Composable
fun DisplayBoard(charBoard: CharBoard, highligh: Boolean) {


    val backgroundColor = if (charBoard.isChar) {

        if (highligh) {
            Color.Green
        } else {
            Color.White
        }


    } else Color.DarkGray.copy(alpha = .5f)

    val fontColor = if (charBoard.isVisible) {
        Color.Black
    } else {
        Color.LightGray
    }

    val label: String = if (charBoard.isVisible) {
        charBoard.char.toString()
    } else {
        "?"
    }

    Surface(
        modifier = Modifier
            .size(40.dp),
        color = backgroundColor,
         elevation = 1.dp,
        shape = RoundedCornerShape(8.dp)
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (charBoard.isChar) {

                Text(
                    text = label,

                    style = MaterialTheme.typography.h4.copy(
                        color = fontColor,
                        fontWeight = FontWeight.Black
                        )
                )
            }
        }
    }


}