package com.jetpack.carpartsfinder.view.component

import android.content.res.Resources
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.jetpack.carpartsfinder.ui.theme.CarPartsFinderTheme
import com.jetpack.carpartsfinder.ui.theme.onSurfaceMedium

@Composable
fun CircularProgressView(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.onSurfaceMedium,
) {
    Surface(color = backgroundColor, modifier = modifier) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ContentWithProgressPreview() {
    CarPartsFinderTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            CircularProgressView(backgroundColor = MaterialTheme.colors.background)
        }
    }
}
