package com.jetpack.carpartsfinder.dto

sealed class SinglePartViewState {
    data class Loading(val id: String? = null) : SinglePartViewState()
    data class ServerError(val id: String) : SinglePartViewState()
    data class NotFound(val id: String) : SinglePartViewState()
    data class Loaded(val partData: SinglePartData) : SinglePartViewState()

    //    data class LoadedImages(val images: PartData) : SinglePartViewState()
    data class ImageZoomed(val url: String) : SinglePartViewState()
}
