package com.jetpack.carpartsfinder.util

import javax.inject.Singleton

@Singleton
interface RemoteConfigInterface {
    fun getBaseUrl(): String
}
