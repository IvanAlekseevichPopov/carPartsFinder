package com.jetpack.uikit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

//private val DarkColorPalette = darkColors(
//    primary = Purple200,
//    primaryVariant = Purple700,
//    secondary = Teal200
//)
//
//private val LightColorPalette = lightColors(
//    primary = Purple500,
//    primaryVariant = Purple700,
//    secondary = Teal200
//)

@Composable
fun AppMaterialTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors()
    } else {
        lightColors()
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

//val Colors.onSurfaceHigh: Color
//    get() = onSurface.copy(alpha = 0.87f)

val Colors.onSurfaceMedium: Color
    get() = onSurface.copy(alpha = 0.5f)

//val Colors.onSurfaceDisabled: Color
//    get() = onSurface.copy(alpha = 0.3f)

//val Colors.onSurfaceStroke: Color
//    get() = onSurface.copy(alpha = 0.12f)
