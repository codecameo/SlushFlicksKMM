package com.sifat.slushflicks.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.sifat.slushflicks.R
import com.sifat.slushflicks.Route
import com.sifat.slushflicks.component.splash.SplashViewModel
import com.sifat.slushflicks.viewevents.SplashViewEvent.UpdateGenreEvent
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel

@Composable
fun SplashScreen(onTimeout: (String) -> Unit) {
    val splashTimeout = 3000L
    val splashViewModel = getViewModel<SplashViewModel>()
    val currentOnTimeout by rememberUpdatedState(onTimeout)
    LaunchedEffect(true) {
        splashViewModel.viewEventState.value = UpdateGenreEvent
        delay(splashTimeout)
        currentOnTimeout(Route.HOME)
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier.wrapContentSize(),
            style = MaterialTheme.typography.h2,
            textAlign = TextAlign.Center
        )
    }
}
