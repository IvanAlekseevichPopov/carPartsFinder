package com.jetpack.carpartsfinder.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.carpartsfinder.dto.PartData
import com.jetpack.carpartsfinder.dto.PartViewState
import com.jetpack.carpartsfinder.view.component.ImageGalleryView
import com.jetpack.carpartsfinder.view.component.SpacerType
import com.jetpack.carpartsfinder.view.component.SpacerView

@Composable
fun SinglePartScreenView(
    screenState: PartViewState,
) {

    if (screenState.partData == null) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            CircularProgressIndicator(
                Modifier.size(100.dp) //TODO size from theme
            )
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
//                .padding(16.dp),
        ) {
            Row {
                Text(
                    text = screenState.partData.manufacturer,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h5,
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = screenState.partData.partNumber,
                    style = MaterialTheme.typography.h5
                )
            }
            SpacerView(type = SpacerType.Horizontal(12.dp))
            Surface(
                color = Color.Red,
                modifier = Modifier.fillMaxSize()
            ) {
                ImageGalleryView(images = screenState.partData.images.map { it.path })
            }
        }
    }
}

@Composable
@Preview
private fun PreviewSinglePartScreenView() {
    return SinglePartScreenView(
        screenState = PartViewState(
            partData = PartData(
                id = "1",
                manufacturer = "BMW",
                images = listOf(),
                partNumber = "1234567890",
            )
        )
    )
}

@Composable
@Preview
private fun PreviewLoadingSinglePartScreenView() {
    return SinglePartScreenView(
        screenState = PartViewState.Loading
    )
}


