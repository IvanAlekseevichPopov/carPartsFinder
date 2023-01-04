package com.jetpack.carpartsfinder.network

data class PartResponse(
    val id: Int,
    val partNumber: String,
    val manufacturer: String,
    val previewImage: String
)
