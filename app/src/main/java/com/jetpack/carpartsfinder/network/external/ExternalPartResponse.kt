package com.jetpack.carpartsfinder.network.external

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalPartResponse(
    @SerialName("id") val manufacturerId: Int,
    @SerialName("artNumber") val partNumber: String,
    @SerialName("manufacturerName") val manufacturer: String,
)
