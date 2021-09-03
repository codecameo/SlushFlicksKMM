package com.sifat.slushflicks.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sifat.slushflicks.Route
import com.sifat.slushflicks.component.home.HomeScreen

@Composable
fun SlushFlicksNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Route.SPLASH,
) {
    val navigation = remember { Navigation(navController = navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Route.SPLASH) {
            SplashScreen(navigation::navigateTo)
        }
        composable(Route.HOME) {
            HomeScreen()
        }
    }
}

class Navigation(private val navController: NavHostController) {
    fun navigateTo(route: String) {
        navController.navigate(route) {
            popUpTo(Route.SPLASH) {
                inclusive = true
            }
        }
    }
}
