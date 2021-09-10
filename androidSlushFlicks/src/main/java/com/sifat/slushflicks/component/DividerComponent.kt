package com.sifat.slushflicks.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DividerComponent(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 6.dp)
        )
        Spacer(
            modifier = Modifier
                .height(12.dp)
                .width(1.dp)
                .background(MaterialTheme.colors.onSecondary)
        )
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 6.dp)
        )
    }
}
