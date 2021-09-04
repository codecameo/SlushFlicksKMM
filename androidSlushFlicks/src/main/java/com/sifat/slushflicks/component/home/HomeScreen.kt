package com.sifat.slushflicks.component.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController.OnDestinationChangedListener
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.sifat.slushflicks.R
import com.sifat.slushflicks.Route
import com.sifat.slushflicks.component.about.AboutScreen
import com.sifat.slushflicks.component.movie.MovieScreen
import com.sifat.slushflicks.component.search.SearchScreen
import com.sifat.slushflicks.component.tvshow.TvShowScreen

@ExperimentalAnimationApi
@Composable
fun HomeScreen() {
    var currentRoute by remember { mutableStateOf(Route.MOVIE) }
    val navController = rememberAnimatedNavController()
    val navigationListener = OnDestinationChangedListener { _, destination, _ ->
        destination.route?.let { route ->
            currentRoute = route
        }
    }

    DisposableEffect(navigationListener) {
        navController.addOnDestinationChangedListener(navigationListener)
        onDispose {
            navController.removeOnDestinationChangedListener(navigationListener)
        }
    }
    Scaffold(
        bottomBar = {
            HomeBottomNav(currentRoute = currentRoute) {
                if (it == currentRoute) return@HomeBottomNav
                currentRoute = it
                if (currentRoute == Route.MOVIE) {
                    navController.popBackStack()
                } else {
                    navController.navigate(it) {
                        popUpTo(Route.MOVIE)
                    }
                }
            }
        }
    ) {
        AnimatedNavHost(navController = navController, startDestination = Route.MOVIE) {
            composable(
                Route.MOVIE,
                enterTransition = { _, _ ->
                    slideInHorizontally(initialOffsetX = { 1000 })
                },
                exitTransition = { _, _ ->
                    slideOutHorizontally(targetOffsetX = { -1000 })
                },
                popExitTransition = { _, _ ->
                    slideOutHorizontally(targetOffsetX = { 1000 })
                },
                popEnterTransition = { _, _ ->
                    slideInHorizontally(initialOffsetX = { -1000 })
                }
            ) {
                MovieScreen()
            }
            composable(
                Route.TV_SHOW,
                enterTransition = { _, _ ->
                    slideInHorizontally(initialOffsetX = { 1000 })
                },
                exitTransition = { _, _ ->
                    slideOutHorizontally(targetOffsetX = { -1000 })
                },
                popExitTransition = { _, _ ->
                    slideOutHorizontally(targetOffsetX = { 1000 })
                },
                popEnterTransition = { _, _ ->
                    slideInHorizontally(initialOffsetX = { -1000 })
                }
            ) {
                TvShowScreen()
            }
            composable(
                Route.SEARCH,
                enterTransition = { _, _ ->
                    slideInHorizontally(initialOffsetX = { 1000 })
                },
                exitTransition = { _, _ ->
                    slideOutHorizontally(targetOffsetX = { -1000 })
                },
                popExitTransition = { _, _ ->
                    slideOutHorizontally(targetOffsetX = { 1000 })
                },
                popEnterTransition = { _, _ ->
                    slideInHorizontally(initialOffsetX = { -1000 })
                }
            ) {
                SearchScreen()
            }
            composable(
                Route.ABOUT,
                enterTransition = { _, _ ->
                    slideInHorizontally(initialOffsetX = { 1000 })
                },
                exitTransition = { _, _ ->
                    slideOutHorizontally(targetOffsetX = { -1000 })
                },
                popExitTransition = { _, _ ->
                    slideOutHorizontally(targetOffsetX = { 1000 })
                },
                popEnterTransition = { _, _ ->
                    slideInHorizontally(initialOffsetX = { -1000 })
                }
            ) {
                AboutScreen()
            }
        }
    }
}

@Composable
fun HomeBottomNav(currentRoute: String, onItemSelected: (String) -> Unit) {
    BottomNavigation(
        modifier = Modifier
            .background(MaterialTheme.colors.surface)
            .navigationBarsPadding()
    ) {
        bottomNavigationItems.forEach { bottomNavigationItem ->
            val isSelected = currentRoute == bottomNavigationItem.route
            val tint by animateColorAsState(
                if (isSelected) {
                    MaterialTheme.colors.secondary
                } else {
                    MaterialTheme.colors.onPrimary
                }
            )
            BottomNavigationItem(
                modifier = Modifier.background(MaterialTheme.colors.surface),
                icon = {
                    Icon(
                        painter = painterResource(id = bottomNavigationItem.icon),
                        contentDescription = bottomNavigationItem.route,
                        tint = tint
                    )
                },
                selected = isSelected,
                onClick = {
                    onItemSelected(bottomNavigationItem.route)
                }
            )
        }
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.addHomeGraph() {
    composable(Route.MOVIE) {
        MovieScreen()
    }
    composable(Route.TV_SHOW) {
        TvShowScreen()
    }
    composable(Route.SEARCH) {
        SearchScreen()
    }
    composable(Route.ABOUT) {
        AboutScreen()
    }
}

val bottomNavigationItems = listOf(
    BottomNavigationItem(route = Route.MOVIE, icon = R.drawable.ic_movie),
    BottomNavigationItem(route = Route.TV_SHOW, icon = R.drawable.ic_tv_show),
    BottomNavigationItem(route = Route.SEARCH, icon = R.drawable.ic_search),
    BottomNavigationItem(route = Route.ABOUT, icon = R.drawable.ic_about)
)

data class BottomNavigationItem(val route: String, val icon: Int)
