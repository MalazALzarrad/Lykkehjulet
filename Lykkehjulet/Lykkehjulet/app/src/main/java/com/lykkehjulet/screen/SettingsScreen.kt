package com.lykkehjulet.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.lykkehjulet.SpinViewModel
import com.lykkehjulet.data.Puzzle
import com.lykkehjulet.ui.components.ButtonStyle
import com.lykkehjulet.ui.theme.TextFeildBackground

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SettingsScreen(viewmodel: SpinViewModel, function: () -> Unit) {

   val list =  viewmodel.puzzleRepository.getAllPuzzleFlow().collectAsState(initial = emptyList()).value

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    var downloadLink: TextFieldValue by remember { mutableStateOf(TextFieldValue("")) }

    val insert : (String)-> Unit = {

        viewmodel.insert(Puzzle(value = it))
    }

    val delete: (Puzzle) -> Unit = {
        viewmodel.delete(it)
    }


    Column(Modifier.fillMaxSize()) {


        Box (modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
            Alignment.Center
        ) {

            Row(modifier = Modifier
                .fillMaxWidth()
                .height(80.dp).padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {

                IconButton(
                    modifier = Modifier.size(48.dp),
                    onClick = {
                        function()
                    }
                ) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        "contentDescription",
                        modifier = Modifier.size(36.dp)
                    )
                }



                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.h6.copy(
                        color = Color.Black
                    ),
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp)
                )

            }


        }

        Column (modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)

        ) {

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(60.dp)) {
                TextField(
                    value = downloadLink,
                    onValueChange = { value ->
                        downloadLink = value
                    },
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(top = 2.dp, start = 8.dp, end = 8.dp),
                    shape = RoundedCornerShape(8.dp),

                    textStyle = TextStyle(
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = MaterialTheme.typography.subtitle1.fontSize
                    ),
                    placeholder = {
                        Text(
                            text = "",
                            style = TextStyle(
                                color = MaterialTheme.colors.onPrimary.copy(alpha = .2f),
                                fontSize = MaterialTheme.typography.subtitle2.fontSize
                            )
                        )
                    },

                    singleLine = true,
                    trailingIcon = {

                        if (downloadLink != TextFieldValue("")) {
                            IconButton(
                                onClick = {
                                    downloadLink = TextFieldValue("")
                                    // Remove text from TextField when you press the 'X' icon
                                }
                            ) {
                                Icon(
                                    Icons.Default.Close,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(24.dp),
                                    tint = MaterialTheme.colors.onPrimary
                                )
                            }
                        }

                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {keyboardController?.hide()}),

                    colors = TextFieldDefaults.textFieldColors(
                        textColor = MaterialTheme.colors.onSurface,
                        cursorColor = MaterialTheme.colors.secondary,
                        leadingIconColor = MaterialTheme.colors.onPrimary,
                        trailingIconColor = MaterialTheme.colors.onPrimary,
                        backgroundColor = TextFeildBackground,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(60.dp)) {

                ButtonStyle(FontColor = Color.White, label = "Add") {

                    if (downloadLink.text.isNotBlank()) {
                        insert(downloadLink.text)
                        downloadLink = TextFieldValue("")
                    }

                }
            }

        }


        Box (modifier = Modifier.fillMaxWidth()) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 100.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                items(list.size) { division ->
                    DisplayPluzz(list[division]) {
                        delete(list[division])
                    }
                    Divider(modifier = Modifier.fillMaxWidth(), thickness = .5.dp, color = Color.DarkGray)
                }
            }


        }

    }


}

@Composable
fun DisplayPluzz(puzzle: Puzzle , delete : () -> Unit) {


    Row(
        Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {

        Text(
            text = puzzle.value,
            style = MaterialTheme.typography.subtitle2.copy(
                color = Color.Black
            ),
            modifier = Modifier.fillMaxWidth().weight(1f)
        )




        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = {
                delete()
            }
        ) {
            Icon(
                Icons.Default.Delete,
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp),
                tint = Color.Black
            )
        }

    }


}
