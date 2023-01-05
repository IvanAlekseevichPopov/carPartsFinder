package com.jetpack.carpartsfinder

import android.app.Application
import com.jetpack.carpartsfinder.utils.Analytics
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PartApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        var afDevKey: String? = resources.getString(R.string.appsflyerDevKey)
        if(afDevKey?.isEmpty() == true) {
            afDevKey = null
        }

        Analytics.init(afDevKey, this.applicationContext)
    }
}
