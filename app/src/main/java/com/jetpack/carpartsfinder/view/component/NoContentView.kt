package com.jetpack.carpartsfinder.view.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jetpack.carpartsfinder.ui.theme.AppTheme

@Composable
fun NoContentView(
    modifier: Modifier = Modifier,
    errorText: String = "Can't find nothing",
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            errorText //TODO trans
        )
    }
}

@Preview(name = "Light Theme")
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun NoContentViewPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            NoContentView()
        }
    }
}
