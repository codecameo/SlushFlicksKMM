package com.sifat.slushflicks.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.sifat.slushflicks.Arguments.MOVIE_ID
import com.sifat.slushflicks.Route
import com.sifat.slushflicks.Route.MOVIE_DETAILS
import com.sifat.slushflicks.component.details.movie.MovieDetailsScreen
import com.sifat.slushflicks.component.home.HomeScreen
import com.sifat.slushflicks.component.splash.SplashScreen
import kotlinx.coroutines.FlowPreview

@ExperimentalCoilApi
@FlowPreview
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
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
            HomeScreen(navigation::navigateTo)
        }
        composable("$MOVIE_DETAILS{$MOVIE_ID}") {
            (it.arguments?.get(MOVIE_ID) as? String)?.let { movieId ->
                MovieDetailsScreen(onBack = navigation::popBack, movieId = movieId.toLong())
            }
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

    fun navigateTo(route: String, showId: Long) {
        navController.navigate(route = "$route$showId")
    }

    fun popBack() {
        navController.popBackStack()
    }
}
