package com.jetpack.carpartsfinder.dto

data class ParData(
    val id: String,
    val partNumber: String,
    val manufacturer: String,
    val images: List<Image>,
)
