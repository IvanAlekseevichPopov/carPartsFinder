package com.jetpack.carpartsfinder.dto

data class PartViewState(
    val partData: ParData?,
    val isLoading: Boolean,
) {
    companion object {
        val Loading = PartViewState(
            partData = null,
            isLoading = true,
        )
    }
}
