package com.jetpack.carpartsfinder.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.carpartsfinder.dto.SinglePartViewState
import com.jetpack.carpartsfinder.ui.theme.AppTheme
import com.jetpack.carpartsfinder.view.component.CircularProgressView
import com.jetpack.carpartsfinder.view.component.HorizontalSpacer
import com.jetpack.carpartsfinder.view.component.ImageGalleryView
import com.jetpack.carpartsfinder.view.component.ImageScreen
import com.jetpack.carpartsfinder.view.component.NoContentView
import com.jetpack.carpartsfinder.view.component.ServerErrorView
import com.jetpack.carpartsfinder.view.component.VerticalSpacer
import com.jetpack.carpartsfinder.viewmodel.UIPartViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun SinglePartScreenView(
    viewModel: UIPartViewModel
) {
    val partDataState = viewModel.partDataState.collectAsState()

    when (partDataState.value) {
        is SinglePartViewState.Loading -> {
            CircularProgressView()
        }
        is SinglePartViewState.ServerError -> {
            ServerErrorView(onButtonClick = {
                //TODO refresh screen
            })
        }
        is SinglePartViewState.NotFound -> {
            NoContentView(errorText = "Part not found")
        }
        is SinglePartViewState.ImageZoomed -> {
            val image = (partDataState.value as SinglePartViewState.ImageZoomed).url
            ImageScreen(image, onBack = {
                viewModel.triggerImageHide()
            })
        }
        is SinglePartViewState.Loaded -> {
            val part = (partDataState.value as SinglePartViewState.Loaded).partData
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Row {
                    //TODO onclick on manufacturer
                    Text(
                        text = part.manufacturer,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h5,
                    )
                    HorizontalSpacer(size = 12.dp)
                    Text(
                        text = part.partNumber,
                        style = MaterialTheme.typography.h5
                    )
                }
                VerticalSpacer(size = 12.dp)
                Row {
                    ImageGalleryView(
                        images = part.images.map { it.path },
                        onImageClick = {
                            viewModel.triggerImageClick(it)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewSinglePartScreenView() {
    AppTheme {
        SinglePartScreenView(
            viewModel = object : UIPartViewModel {
                override val partDataState = MutableStateFlow<SinglePartViewState>(SinglePartViewState.Loading).asStateFlow()
                override fun triggerImageClick(url: String) {}
                override fun triggerImageHide() {}
            }
        )
    }
}

