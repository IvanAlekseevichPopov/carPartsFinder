package com.jetpack.carpartsfinder.mapper

import com.jetpack.carpartsfinder.dto.PartListItemData
import com.jetpack.carpartsfinder.network.PartResponse
import javax.inject.Inject

class PartListMapper @Inject constructor() {
    fun map(partResponse: PartResponse): PartListItemData {
        return PartListItemData(
            id = partResponse.id,
            partNumber = partResponse.partNumber,
            manufacturer = partResponse.manufacturer,
            previewImage = partResponse.previewImage
        )
    }
}
