package com.jetpack.carpartsfinder.utils

import javax.inject.Singleton

@Singleton
interface RemoteConfigInterface {
    fun getBaseUrl(): String
}
