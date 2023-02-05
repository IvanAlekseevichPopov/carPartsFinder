package com.jetpack.carpartsfinder.mapper

import com.jetpack.carpartsfinder.dto.ImageData
import com.jetpack.carpartsfinder.network.ImageResponse
import javax.inject.Inject

class ImageMapper @Inject constructor() {
    fun map(imageResponse: ImageResponse): ImageData {
        return ImageData(
            id = imageResponse.id,
            path = imageResponse.path,
            rating = imageResponse.rating
        )
    }
}
