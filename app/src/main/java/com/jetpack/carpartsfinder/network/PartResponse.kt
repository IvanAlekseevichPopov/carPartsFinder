package com.jetpack.carpartsfinder.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PartResponse(
    @SerialName("id") val id: String,
    @SerialName("partNumber") val partNumber: String,
    @SerialName("name") val name: String,
    @SerialName("manufacturer") val manufacturer: String,
    @SerialName("previewImage") val previewImage: String
)
