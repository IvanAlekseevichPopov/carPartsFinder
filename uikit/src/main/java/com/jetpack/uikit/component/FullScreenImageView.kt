package com.jetpack.uikit.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.jetpack.uikit.theme.dimens
import com.jetpack.uikit.theme.onSurfaceMedium
import com.mxalbert.zoomable.Zoomable

@Composable
fun FullScreenImageView(
    modifier: Modifier = Modifier,
    url: String,
    onDismissAction: () -> Boolean
) {
    Zoomable(modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background),
        dismissGestureEnabled = true,
        onDismiss = { onDismissAction() }
    ) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .diskCacheKey(url)
                .diskCachePolicy(coil.request.CachePolicy.ENABLED)
                .data(url)
                .size(Size.ORIGINAL)
                .build()
        )
        if (painter.state is AsyncImagePainter.State.Success) {
            val size = painter.intrinsicSize
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(size.width / size.height)
                    .shadow(MaterialTheme.dimens.four)
//                    .border(1.dp, MaterialTheme.colors.onBackground)
                    .fillMaxSize()
            )
        } else {
            CircularProgressView()
        }
    }
}

@Composable
fun ImageScreen(url: String, onBack: () -> Unit) {
    BackHandler(onBack = onBack)
    FullScreenImageView(url = url, onDismissAction = {
        onBack()
        true
    })
}
