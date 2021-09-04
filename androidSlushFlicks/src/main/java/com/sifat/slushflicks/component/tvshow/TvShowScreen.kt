package com.sifat.slushflicks.component.tvshow

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TvShowScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(text = "TvShow")
    }
}