package com.jetpack.carpartsfinder.viewmodel

import com.jetpack.carpartsfinder.dto.PartData
import kotlinx.coroutines.flow.StateFlow

interface UIPartViewModel {
    val partDataState: StateFlow<PartData?>
    val zoomedImage: StateFlow<String?>

    fun triggerImageClick(url: String)
    fun triggerImageHide()
}
