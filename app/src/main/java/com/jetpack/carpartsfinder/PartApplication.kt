package com.jetpack.carpartsfinder

import android.app.Application
import com.jetpack.carpartsfinder.utils.Analytics
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PartApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Analytics.init(this.applicationContext)
//        RemoteConfig.initFirebaseRemoteConfig()
    }
}
