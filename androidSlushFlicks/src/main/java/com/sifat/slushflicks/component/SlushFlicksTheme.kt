package com.sifat.slushflicks.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.sifat.slushflicks.theme.SlushFlicksShapes
import com.sifat.slushflicks.theme.SlushFlicksTypography

private val LightThemeColors = lightColors(
    primary = Color(0xff1d1f2b),
    primaryVariant = Color(0xFF1A2224),
    onPrimary = Color.White,
    secondary = Color(0xffFE9224),
    secondaryVariant = Color(0xffFE9224),
    onSecondary = Color(0xFFD2D2D2),
    error = Color(0xffe52346),
    background = Color(0xff1d1f2b),
    onBackground = Color.White,
    surface = Color(0xff121212),
    onSurface = Color.White
)

private val DarkThemeColors = darkColors(
    primary = Color(0xff1d1f2b),
    primaryVariant = Color(0xFF1A2224),
    onPrimary = Color.White,
    secondary = Color(0xffFE9224),
    secondaryVariant = Color(0xffFE9224),
    onSecondary = Color(0xFFD2D2D2),
    error = Color(0xffe52346),
    background = Color(0xff1d1f2b),
    onBackground = Color.White,
    surface = Color(0xff121212),
    onSurface = Color.White
)

@Composable
fun SlushFlicksTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = SlushFlicksTypography,
        shapes = SlushFlicksShapes,
        content = content
    )
}
