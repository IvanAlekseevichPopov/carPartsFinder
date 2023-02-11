package com.jetpack.carpartsfinder.viewmodel

import com.jetpack.carpartsfinder.dto.PartListViewState
import kotlinx.coroutines.flow.StateFlow

interface UiPartListViewModel {
    val uiState: StateFlow<PartListViewState>

    fun search(searchString: String?)
}
