package com.jetpack.carpartsfinder.mapper

import com.jetpack.carpartsfinder.dto.PartListItemData
import com.jetpack.carpartsfinder.network.PartResponse
import com.jetpack.carpartsfinder.network.external.ExternalPartResponse
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

    fun mapExternal(partResponse: ExternalPartResponse): PartListItemData {
        return PartListItemData(
            id = partResponse.id.toString(),
            partNumber = partResponse.partNumber,
            manufacturer = partResponse.manufacturer,
            previewImage = ""
        )
    }
}
