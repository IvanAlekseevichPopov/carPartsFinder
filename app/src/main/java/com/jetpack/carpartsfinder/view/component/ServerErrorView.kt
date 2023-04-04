package com.jetpack.carpartsfinder.view.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jetpack.carpartsfinder.ui.theme.AppTheme

@Composable
fun ServerErrorView(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
    errorText: String = "Server error. Come back later",
    buttonText: String = "Refresh",
) {
    Column( //TODO refresh by swipe down
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = errorText)
        Button(
            onClick = onButtonClick
        ) {
            Text(buttonText)
        }
    }
}

@Preview(name = "Light Theme")
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ServerErrorViewPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            ServerErrorView(
                onButtonClick = { }
            )
        }
    }
}
