package com.jetpack.carpartsfinder.view.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalInspectionMode

@Composable
fun PreviewImagePlaceholder(): Painter? {
    if (LocalInspectionMode.current) {
        return ColorPainter(Color.Blue)
    } else {
        return null
    }
}
