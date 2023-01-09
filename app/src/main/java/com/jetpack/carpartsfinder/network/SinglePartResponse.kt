package com.jetpack.carpartsfinder.network

data class SinglePartResponse(
    val id: String,
    val partNumber: String,
    val manufacturer: String,
    val images: List<String>
)
