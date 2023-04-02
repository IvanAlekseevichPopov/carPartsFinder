package com.jetpack.carpartsfinder.network

import com.jetpack.carpartsfinder.dto.PartData
import com.jetpack.carpartsfinder.dto.PartListItemData
import com.jetpack.carpartsfinder.mapper.PartListMapper
import com.jetpack.carpartsfinder.mapper.PartMapper
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PartRepository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val partMapper: PartMapper,
    private val partListMapper: PartListMapper,
) {
    suspend fun getParts(searchString: String?): List<PartListItemData> {
        val networkResponse = apiInterface.getParts(searchString)

        return partListMapper.map(networkResponse)
    }

    suspend fun getPart(uuid: String): PartData {
        val networkResponse = apiInterface.getPart(uuid)

        return partMapper.map(networkResponse)
    }

    suspend fun getPartImages(partId: String): List<ImageResponse> {
        return apiInterface.getPartImages(partId)
    }
}
