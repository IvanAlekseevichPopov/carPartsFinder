package com.jetpack.carpartsfinder.network

data class PartResponse(
    val id: String,
    val partNumber: String,
    val manufacturer: String,
    val previewImage: String
)
