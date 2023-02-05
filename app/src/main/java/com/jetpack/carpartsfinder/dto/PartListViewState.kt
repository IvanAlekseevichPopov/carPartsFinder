package com.jetpack.carpartsfinder.dto

import com.jetpack.carpartsfinder.network.PartResponse

data class PartListViewState(
    val parts: List<PartListItemData>,
    val inputText: String? = null,
    val isLoading: Boolean,
) {
    companion object {
        val InitialLoading = PartListViewState(
            parts = emptyList(),
            inputText = null,
            isLoading = true,
        )
    }
}
