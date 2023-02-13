package com.jetpack.uikit.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun VerticalSpacer(size: Dp, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(size))
}

@Composable
fun HorizontalSpacer(size: Dp, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.width(size))
}
