package com.jetpack.carpartsfinder.mapper

import com.jetpack.carpartsfinder.dto.PartListItemData
import com.jetpack.carpartsfinder.network.PartResponse
import com.jetpack.carpartsfinder.util.RemoteConfigInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartListMapper @Inject constructor(
    private val remoteConfig: RemoteConfigInterface
) {
    fun map(partListResponse: List<PartResponse>): List<PartListItemData> {
        return partListResponse.map { partResponse ->
            map(partResponse)
        }
    }

    private fun map(partResponse: PartResponse): PartListItemData {
        return PartListItemData(
            id = partResponse.id,
            partNumber = partResponse.partNumber,
            manufacturer = partResponse.manufacturer,
            previewImage = generateImageUrl(partResponse)
        )
    }

    private fun generateImageUrl(partResponse: PartResponse): String {
        if (partResponse.previewImage.startsWith("http")) {
            return partResponse.previewImage
        }

        return remoteConfig.getImagesBaseUrl() + "/" + partResponse.previewImage
    }
}
