package com.jetpack.carpartsfinder.dto

enum class DataReceivingStatus {
    Loading,
    ServerError,
    Loaded,
}

data class PartListViewState(
    val parts: List<PartListItemData> = emptyList(),
    val inputText: String? = null,
    val status: DataReceivingStatus
) {
    companion object {
        val Loading = PartListViewState(
            status = DataReceivingStatus.Loading
        )

        val ServerError = PartListViewState(
            status = DataReceivingStatus.ServerError
        )

        val LoadedState = PartListViewState(
            status = DataReceivingStatus.Loaded
        )
    }
}

//TODO try sealed class
//sealed class PartListViewState {
//    object Loading : PartListViewState()
//    object ServerError : PartListViewState()
//    data class Loaded(val parts: List<PartListItemData>) : PartListViewState()
//}
