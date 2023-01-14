package com.jetpack.carpartsfinder.dto

data class PartViewState(
    val partData: ParData?,
) {
    companion object {
        val Loading = PartViewState(
            partData = null,
        )
    }
}
