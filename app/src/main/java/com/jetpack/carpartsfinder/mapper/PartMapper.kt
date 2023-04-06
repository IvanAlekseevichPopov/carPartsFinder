package com.jetpack.carpartsfinder.mapper

import com.jetpack.carpartsfinder.dto.PartData
import com.jetpack.carpartsfinder.network.SinglePartResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartMapper @Inject constructor() {
    fun map(partResponse: SinglePartResponse): PartData {
        return PartData(
            id = partResponse.id,
            partName = partResponse.partName,
            partNumber = partResponse.partNumber,
            manufacturer = partResponse.manufacturer,
            images = emptyList()
        )
    }
}
