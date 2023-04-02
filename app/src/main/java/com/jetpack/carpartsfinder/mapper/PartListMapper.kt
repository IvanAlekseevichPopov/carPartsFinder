package com.jetpack.carpartsfinder.mapper

import com.jetpack.carpartsfinder.dto.PartListItemData
import com.jetpack.carpartsfinder.network.PartResponse
import com.jetpack.carpartsfinder.util.RemoteConfig
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

//@ActivityScoped
class PartListMapper
@Inject
constructor(
//    private val remoteConfig: RemoteConfig
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

        return ""
//        return remoteConfig.getImagesBaseUrl() + "/" + partResponse.previewImage
    }
}
