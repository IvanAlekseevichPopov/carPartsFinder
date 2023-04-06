package com.jetpack.carpartsfinder.mapper

import com.jetpack.carpartsfinder.dto.SinglePartData
import com.jetpack.carpartsfinder.network.SinglePartResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartMapper @Inject constructor() {
    fun map(partResponse: SinglePartResponse): SinglePartData {
        return SinglePartData(
            id = partResponse.id,
            partName = partResponse.partName,
            partNumber = partResponse.partNumber,
            manufacturer = partResponse.manufacturer,
            images = emptyList()
        )
    }
}
