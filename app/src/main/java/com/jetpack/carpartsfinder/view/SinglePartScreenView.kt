package com.jetpack.carpartsfinder.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.carpartsfinder.dto.ParData
import com.jetpack.carpartsfinder.dto.PartViewState

//@ExperimentalMaterialApi
@Composable
fun SinglePartScreenView(
    partViewState: PartViewState,
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            if (partViewState.isLoading) {
                CircularProgressIndicator(
                    Modifier.size(100.dp)
                )
            } else {
                partViewState.partData?.let { partData ->
                    Text(text = partData.id)
                    Text(text = partData.manufacturer)
//                    Text(text = partData.partNumber)
                }
            }
        }

    }
}

@Composable
@Preview
private fun PreviewSinglePartScreenView() {
    return SinglePartScreenView(
        partViewState = PartViewState.Loading
    )
}
