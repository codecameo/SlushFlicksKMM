package com.sifat.slushflicks.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
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
