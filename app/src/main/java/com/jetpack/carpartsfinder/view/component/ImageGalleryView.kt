package com.jetpack.carpartsfinder.view.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ImageGalleryView(
    images: List<String>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = modifier
    ) {
        items(images.size) { index ->
            val image = images[index]
            AsyncImage(
                model = image,
                modifier = Modifier.size(128.dp).clip(RoundedCornerShape(8.dp)).padding(8.dp),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                placeholder = PreviewImagePlaceholder(),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAsyncIconView() {
    Box(
        modifier = Modifier
            .height(2400.dp)
            .width(1080.dp)
    ) {
        ImageGalleryView(
            images = listOf(
                "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
            )
        )
    }

}
