package com.jetpack.carpartsfinder.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageResponse (
    @SerialName("id") val id: String,
    @SerialName("path") val path: String,
    @SerialName("rating") val rating: Short?, //number from 1 to 500
)
