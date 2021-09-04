package com.sifat.slushflicks.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets

@ExperimentalAnimationApi
@Composable
fun SlushFlicksApp() {
    ProvideWindowInsets {
        SlushFlicksTheme {
            val navController: NavHostController = rememberNavController()
            SlushFlicksNavGraph(navController = navController)
        }
    }
}
