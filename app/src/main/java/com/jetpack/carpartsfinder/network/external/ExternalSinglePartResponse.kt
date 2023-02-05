package com.jetpack.carpartsfinder.network.external

data class ExternalSinglePartResponse(
    val id: String,
    val partNumber: String,
    val manufacturer: String,

//TODO    val images: List<ImageResponse>
)
