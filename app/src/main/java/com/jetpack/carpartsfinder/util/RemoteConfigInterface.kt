package com.jetpack.carpartsfinder.util

import kotlinx.coroutines.flow.StateFlow
import javax.inject.Singleton

@Singleton
interface RemoteConfigInterface {
    val isConfigReady: StateFlow<Boolean>

    fun getBaseUrl(): String

    fun getImagesBaseUrl(): String
}
