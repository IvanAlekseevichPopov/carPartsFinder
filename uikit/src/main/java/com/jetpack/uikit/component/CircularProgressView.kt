package com.jetpack.uikit.component

//import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.jetpack.uikit.theme.AppMaterialTheme
import com.jetpack.uikit.theme.onSurfaceMedium

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
    AppMaterialTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            CircularProgressView(backgroundColor = MaterialTheme.colors.background)
        }
    }
}
