package com.jetpack.carpartsfinder.network

import com.jetpack.carpartsfinder.dto.SinglePartData
import com.jetpack.carpartsfinder.dto.PartListItemData
import com.jetpack.carpartsfinder.mapper.PartListMapper
import com.jetpack.carpartsfinder.mapper.PartMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PartRepository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val partMapper: PartMapper,
    private val partListMapper: PartListMapper,
) {
    suspend fun getParts(searchString: String?): List<PartListItemData> {
        val networkResponse = apiInterface.getParts(searchString)

        return partListMapper.map(networkResponse)
    }

    suspend fun getPart(uuid: String): SinglePartData {
        val networkResponse = apiInterface.getPart(uuid)

        return partMapper.map(networkResponse)
    }

    suspend fun getPartImages(partId: String): List<ImageResponse> {
        return apiInterface.getPartImages(partId)
    }
}
