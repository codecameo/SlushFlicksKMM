package com.sifat.slushflicks.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.sifat.slushflicks.Arguments.MOVIE_ID
import com.sifat.slushflicks.Arguments.TV_SHOW_ID
import com.sifat.slushflicks.Route.HOME
import com.sifat.slushflicks.Route.MOVIE_DETAILS
import com.sifat.slushflicks.Route.SPLASH
import com.sifat.slushflicks.Route.TV_SHOW_DETAILS
import com.sifat.slushflicks.component.details.movie.MovieDetailsScreen
import com.sifat.slushflicks.component.details.tvshow.TvShowDetailsScreen
import com.sifat.slushflicks.component.home.HomeScreen
import com.sifat.slushflicks.component.splash.SplashScreen
import com.sifat.common.domain.utils.ShowType
import kotlinx.coroutines.FlowPreview

@ExperimentalCoilApi
@FlowPreview
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun SlushFlicksNavGraph(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState,
    navController: NavHostController = rememberNavController(),
    startDestination: String = SPLASH,
) {
    val navigationController by rememberUpdatedState(newValue = navController)
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(SPLASH) {
            SplashScreen {
                navigationController.navigate(HOME) {
                    popUpTo(SPLASH) {
                        inclusive = true
                    }
                }
            }
        }
        composable(HOME) {
            HomeScreen { showType, showId ->
                val route = when (showType) {
                    ShowType.MOVIE -> MOVIE_DETAILS
                    ShowType.TV_SHOW -> TV_SHOW_DETAILS
                }
                navigationController.navigate(route = "$route$showId")
            }
        }
        composable("$MOVIE_DETAILS{$MOVIE_ID}") {
            (it.arguments?.get(MOVIE_ID) as? String)?.let { movieId ->
                MovieDetailsScreen(scaffoldState = scaffoldState, movieId = movieId.toLong()) {
                    navigationController.popBackStack()
                }
            }
        }
        composable("$TV_SHOW_DETAILS{$TV_SHOW_ID}") {
            (it.arguments?.get(TV_SHOW_ID) as? String)?.let { tvShowId ->
                TvShowDetailsScreen(scaffoldState = scaffoldState, tvShowId = tvShowId.toLong()) {
                    navigationController.popBackStack()
                }
            }
        }
    }
}
