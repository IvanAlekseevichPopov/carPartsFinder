package com.jetpack.carpartsfinder.network

data class SinglePartResponse(
    val id: String,
    val partNumber: String,
    val manufacturer: String,
//TODO    val images: List<ImageResponse>
)
