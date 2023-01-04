package com.jetpack.carpartsfinder.view

import androidx.compose.runtime.Composable
import com.jetpack.carpartsfinder.network.PartResponse

//@ExperimentalMaterialApi
@Composable
fun SinglePartScreenView(
    parts: List<PartResponse>?,
    onSearchChange: (String) -> Unit,
) {
}

//@Composable
//@Preview
//private fun PreviewSinglePartScreenView() {
//    return SinglePartScreenView(
//        parts = null,
//    )
//}
