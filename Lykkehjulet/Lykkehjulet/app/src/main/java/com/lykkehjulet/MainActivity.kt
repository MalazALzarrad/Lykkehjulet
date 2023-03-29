package com.lykkehjulet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lykkehjulet.screen.*
import com.lykkehjulet.screen.play.PlayScreen
import com.lykkehjulet.ui.theme.S180424LykkehjuletTheme
import com.lykkehjulet.utils.AppScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            S180424LykkehjuletTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

                    Navigation()

                }
            }
        }
    }
}

@Composable
fun Navigation(viewmodel: SpinViewModel = hiltViewModel()) {

    val navController = rememberNavController()

    fun printNav() {
        navController.visibleEntries.value.forEachIndexed { index, navBackStackEntry ->

            System.out.println("Navigation index $index ${navBackStackEntry.destination.route}")
        }
    }

    NavHost(navController = navController, startDestination = AppScreen.Welcome.route) {

        composable(AppScreen.Welcome.route) {
            Welcome() {
                if (it) {
                    navController.navigate(AppScreen.Play.route)
                } else {
                    navController.navigate(AppScreen.Settings.route)
                }

                printNav()
            }
        }



        composable(AppScreen.Play.route) {

            PlayScreen(viewmodel) {

                navController.popBackStack()
                navController.navigate(AppScreen.GameOver.route)

                printNav()
            }
        }

        composable(AppScreen.GameOver.route) {
            GameOverScreen(viewmodel) {
                navController.popBackStack()
                navController.navigate(AppScreen.Play.route)
                printNav()
            }
        }


        composable(AppScreen.Settings.route) {

            SettingsScreen(viewmodel) {
                navController.popBackStack()
            }


        }

    }


}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    S180424LykkehjuletTheme {
        Greeting("Android")
    }
}