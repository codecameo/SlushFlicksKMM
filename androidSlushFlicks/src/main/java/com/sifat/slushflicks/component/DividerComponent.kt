package com.sifat.slushflicks.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DividerComponent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp)
            .height(10.dp)
            .width(1.dp)
            .background(MaterialTheme.colors.onSecondary.copy(0.8f))
    )
}
