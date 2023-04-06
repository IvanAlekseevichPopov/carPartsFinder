package com.jetpack.carpartsfinder.viewmodel

import com.jetpack.carpartsfinder.dto.SinglePartViewState
import kotlinx.coroutines.flow.StateFlow

interface UIPartViewModel {
    val partDataState: StateFlow<SinglePartViewState>
//    val zoomedImage: StateFlow<String?>

    fun triggerImageClick(url: String)
    fun triggerImageHide()
    fun searchOnePart(uuid: String)
}
