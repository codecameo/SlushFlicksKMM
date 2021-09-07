package com.sifat.slushflicks.component

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultBlendMode

fun Modifier.verticalGradientTint(
    colors: List<Color>,
    blendMode: BlendMode = DefaultBlendMode
) = drawWithContent {
    drawContent()
    drawRect(
        brush = Brush.verticalGradient(colors),
        blendMode = blendMode
    )
}
