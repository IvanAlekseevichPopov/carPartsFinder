package com.jetpack.carpartsfinder.network.external

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.stream.Stream

@Serializable
data class ExternalImageResponse(
    @SerialName("fotoUrls") val images: List<String>,
)

data class ExternalSinglePartResponse constructor(
    val partData: ExternalPartResponse,
    val images: List<String>,
)
