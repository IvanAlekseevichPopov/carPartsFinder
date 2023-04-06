package com.jetpack.carpartsfinder.dto

sealed class PartListViewState {
    data class Loading(val inputText: String? = null): PartListViewState()
    data class ServerError(val inputText: String? = null) : PartListViewState()
    data class Loaded(val parts: List<PartListItemData>, val inputText: String? = null) : PartListViewState()
}
