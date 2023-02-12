package com.jetpack.carpartsfinder.view.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Size
import com.jetpack.carpartsfinder.R
import com.jetpack.carpartsfinder.ui.theme.AppMaterialTheme
import com.jetpack.carpartsfinder.ui.theme.dimens

@Composable
fun ImageGalleryView(
    images: List<String>,
    modifier: Modifier = Modifier,
    space: Dp = MaterialTheme.dimens.eight,
    onImageClick: (String) -> Unit = {}
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = MaterialTheme.dimens.oneHundredTwentyEight),
        verticalArrangement = Arrangement.spacedBy(space),
        horizontalArrangement = Arrangement.spacedBy(space)
    ) {
        items(images.size) { index ->
            if (LocalInspectionMode.current) {
                //TODO remove image_sample file from built apk file
                val painter = painterResource(id = R.drawable.image_sample)
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(painter.intrinsicSize.width / painter.intrinsicSize.height)
//                        .border(
//                            BorderStroke(1.dp, MaterialTheme.colors.onSurface),
//                            RoundedCornerShape(16.dp)
//                        )
                        .size(MaterialTheme.dimens.oneHundredTwentyEight)
                        .shadow(MaterialTheme.dimens.four)
//                        .clip(RoundedCornerShape(16.dp))
                )
                return@items
            }
            val image = images[index]
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .diskCacheKey(image)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .size(Size.ORIGINAL)
                    .build()
            )
            Box {
                if (painter.state is AsyncImagePainter.State.Success) {
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .aspectRatio(painter.intrinsicSize.width / painter.intrinsicSize.height)
//                            .border(
//                                BorderStroke(1.dp, MaterialTheme.colors.onSurface),
//                                RoundedCornerShape(16.dp)
//                            )
                            .clickable {
                                onImageClick(image)
                            }
                            .size(MaterialTheme.dimens.oneHundredTwentyEight)
                            .shadow(MaterialTheme.dimens.four)
//                            .clip(RoundedCornerShape(16.dp))
                    )
                } else {
                    CircularProgressView()
                }
            }
        }
    }
}

@Preview(name = "Light Theme")
@Preview(name = "Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAsyncIconView() {
    AppMaterialTheme {
        Surface {
            Box(
                modifier = Modifier
                    .height(2400.dp)
                    .width(1080.dp)
            ) {
                ImageGalleryView(
                    images = List(20) {
                        "image_mock_$it"
                    }
                )
            }
        }

    }

}
