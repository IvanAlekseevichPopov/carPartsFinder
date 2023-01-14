package com.jetpack.carpartsfinder.mapper

import com.jetpack.carpartsfinder.dto.PartData
import com.jetpack.carpartsfinder.network.SinglePartResponse
import javax.inject.Inject

class PartMapper @Inject constructor(
    private var imageMapper: ImageMapper
) {
    fun map(partResponse: SinglePartResponse): PartData {
        return PartData(
            id = partResponse.id,
            partNumber = partResponse.partNumber,
            manufacturer = partResponse.manufacturer,
            images = emptyList()
//           todo waiting for backend images = partResponse.images.map { imageMapper.map(it) }
        )
    }
}
