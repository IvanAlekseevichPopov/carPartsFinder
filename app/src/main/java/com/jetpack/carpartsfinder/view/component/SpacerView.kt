package com.jetpack.carpartsfinder.view.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

sealed class SpacerType {
    data class RowWeight(val scope: RowScope) : SpacerType()
    data class ColumnWeight(val scope: ColumnScope) : SpacerType()
    data class Horizontal(val size: Dp) : SpacerType()
    data class Vertical(val size: Dp) : SpacerType()
    object None : SpacerType()
}

@Composable
fun SpacerView(type: SpacerType) {
    val modifier: Modifier
    when (type) {
        is SpacerType.Horizontal -> {
            modifier = Modifier.width(type.size)
        }
        is SpacerType.Vertical -> {
            modifier = Modifier.height(type.size)
        }
        is SpacerType.RowWeight -> {
            with(type.scope) {
                modifier = Modifier.weight(1.0f)
            }
        }
        is SpacerType.ColumnWeight -> {
            with(type.scope) {
                modifier = Modifier.weight(1.0f)
            }
        }
        else -> {
            modifier = Modifier
        }
    }
    Spacer(modifier = modifier)
}
