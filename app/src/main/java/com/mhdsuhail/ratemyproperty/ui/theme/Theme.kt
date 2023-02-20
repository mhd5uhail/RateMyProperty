package com.mhdsuhail.ratemyproperty.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Blue200,
    primaryVariant = Blue100,
    secondary = Teal200,
    background = Color.LightGray,
    surface = Color.LightGray
)

private val LightColorPalette = lightColors(
    primary = Blue200,
    primaryVariant = Blue100,
    secondary = Blue100,
    background = Color.White,
    surface = Color.White,
    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun RateMyPropertyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val colors = if (darkTheme) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent
        )
        DarkColorPalette
    } else {
        systemUiController.setSystemBarsColor(
            color = Color.White
        )
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}