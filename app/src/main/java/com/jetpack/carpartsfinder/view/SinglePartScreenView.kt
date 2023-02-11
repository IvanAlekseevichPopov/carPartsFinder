package com.jetpack.carpartsfinder.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.carpartsfinder.dto.ImageData
import com.jetpack.carpartsfinder.dto.PartData
import com.jetpack.carpartsfinder.dto.PartViewState
import com.jetpack.carpartsfinder.view.component.CircularProgressView
import com.jetpack.carpartsfinder.view.component.HorizontalSpacer
import com.jetpack.carpartsfinder.view.component.ImageGalleryView
import com.jetpack.carpartsfinder.view.component.VerticalSpacer

@Composable
fun SinglePartScreenView(
    screenState: PartViewState,
) {
    if (screenState.partData == null) {
        CircularProgressView()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Row {
                //TODO onclick on manufacturer
                Text(
                    text = screenState.partData.manufacturer,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h5,
                )
                HorizontalSpacer(size = 12.dp)
                Text(
                    text = screenState.partData.partNumber,
                    style = MaterialTheme.typography.h5
                )
            }
            VerticalSpacer(size = 12.dp)
            Row() {
                ImageGalleryView(images = screenState.partData.images.map { it.path })
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewSinglePartScreenView() {
    return SinglePartScreenView(
        screenState = PartViewState(
            partData = PartData(
                id = "1",
                manufacturer = "BMW",
                images = List(15) { ImageData(it.toString(), it.toString(), null) },
                partNumber = "1234567890",
            )
        )
    )
}


