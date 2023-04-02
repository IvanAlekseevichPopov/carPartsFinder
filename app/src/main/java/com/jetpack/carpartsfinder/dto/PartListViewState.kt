package com.jetpack.carpartsfinder.dto

enum class State {
    Loading,
    ServerError,
    Loaded,
}

data class PartListViewState(
    val parts: List<PartListItemData> = emptyList(),
    val inputText: String? = null,
    val state: State
) {
    companion object {
        val Loading = PartListViewState(
            state = State.Loading
        )

        val ServerError = PartListViewState(
            state = State.ServerError
        )

        val LoadedState = PartListViewState(
            state = State.Loaded
        )
    }
}
