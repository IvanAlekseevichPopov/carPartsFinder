package com.jetpack.carpartsfinder.dto

data class PartViewState(
    val partData: PartData?,
) {
    companion object {
        val Loading = PartViewState(
            partData = null,
        )
    }
}
