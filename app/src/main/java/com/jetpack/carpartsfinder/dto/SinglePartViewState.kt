package com.jetpack.carpartsfinder.dto

sealed class SinglePartViewState {
    object Loading : SinglePartViewState()
    object ServerError : SinglePartViewState()
    object NotFound : SinglePartViewState()
    data class Loaded(val partData: PartData) : SinglePartViewState()
//    data class LoadedImages(val images: PartData) : SinglePartViewState()
    data class ImageZoomed(val url: String) : SinglePartViewState()
}
