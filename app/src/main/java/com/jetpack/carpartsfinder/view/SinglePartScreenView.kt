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
import androidx.compose.ui.unit.dp
import com.jetpack.carpartsfinder.ui.theme.dimens
import com.jetpack.carpartsfinder.view.component.CircularProgressView
import com.jetpack.carpartsfinder.view.component.HorizontalSpacer
import com.jetpack.carpartsfinder.view.component.ImageGalleryView
import com.jetpack.carpartsfinder.view.component.ImageScreen
import com.jetpack.carpartsfinder.view.component.VerticalSpacer
import com.jetpack.carpartsfinder.viewmodel.UIPartViewModel

@Composable
fun SinglePartScreenView(
    viewModel: UIPartViewModel
) {
    val partDataState = viewModel.partDataState.collectAsState()
    val zoomedImage = viewModel.zoomedImage.collectAsState()

    partDataState.value?.let { partData ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.dimens.eight)
        ) {
            Row {
                //TODO onclick on manufacturer
                Text(
                    text = partData.manufacturer,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h5,
                )
                HorizontalSpacer(size = MaterialTheme.dimens.twelve)
                Text(
                    text = partData.partNumber,
                    style = MaterialTheme.typography.h5
                )
            }
            VerticalSpacer(size = MaterialTheme.dimens.twelve)
            Row {
                ImageGalleryView(
                    images = partData.images.map { it.path },
                    onImageClick = {
                        viewModel.triggerImageClick(it)
                    }
                )
            }
        }

        zoomedImage.value?.let {
            ImageScreen(it, onBack = {
                viewModel.triggerImageHide()
            })
        }
    } ?: run {
        CircularProgressView()
    }
}


