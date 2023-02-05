package com.jetpack.carpartsfinder.dto

data class PartData(
    val id: String,
    val partNumber: String,
    val manufacturer: String,
    val images: List<ImageData>,
)
