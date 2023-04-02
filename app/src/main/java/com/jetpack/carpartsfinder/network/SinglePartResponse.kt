package com.jetpack.carpartsfinder.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SinglePartResponse(
    @SerialName("id") val id: String,
    @SerialName("partNumber") val partNumber: String,
    @SerialName("manufacturer") val manufacturer: String,
)
