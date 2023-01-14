package com.jetpack.carpartsfinder.utils

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.jetpack.carpartsfinder.BuildConfig
import com.jetpack.carpartsfinder.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val FETCH_INTERVAL_IN_SECONDS = 60 * 30L

@InstallIn(SingletonComponent::class)
@Module
class RemoteConfig: RemoteConfigInterface {
    private lateinit var baseUrl: String

    private var loaded = false

    @Singleton
    @Provides
    fun provideRemoteConfig(): RemoteConfigInterface {
        FirebaseRemoteConfig.getInstance().apply {
            if (BuildConfig.DEBUG) {
                reset()
            }
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = FETCH_INTERVAL_IN_SECONDS
            }
            setConfigSettingsAsync(configSettings)


            setDefaultsAsync(R.xml.remote_config).addOnSuccessListener {
                applyFirebaseConfig(FirebaseRemoteConfig.getInstance())
            }

            if (!BuildConfig.DEBUG) {
                fetchAndActivate().addOnSuccessListener {
                    applyFirebaseConfig(FirebaseRemoteConfig.getInstance())
                    loaded = true
                }
            }

            FirebaseRemoteConfig.getInstance().fetchAndActivate()
        }

        return this
    }

    private fun applyFirebaseConfig(config: FirebaseRemoteConfig) {
        baseUrl = config.getString("baseUrl")
    }

    //TODO maybe suspend will work here?
    override fun getBaseUrl(): String {
//        while (loaded != true ) {
//            Thread.sleep(100)
//        }
        return this.baseUrl
    }

}

//object RemoteConfig {
//    private const val FETCH_INTERVAL_IN_SECONDS = 60 * 30L
//
//    private lateinit var baseUrl: String
//
//    fun initFirebaseRemoteConfig() {
//        //TODO защита от повторного инита
//        FirebaseRemoteConfig.getInstance().apply {
//            if (BuildConfig.DEBUG) {
//                reset()
//            }
//            val configSettings = remoteConfigSettings {
//                minimumFetchIntervalInSeconds = FETCH_INTERVAL_IN_SECONDS
//            }
//            setConfigSettingsAsync(configSettings)
//
//            setDefaultsAsync(R.xml.remote_config).addOnSuccessListener {
//                applyFirebaseConfig(FirebaseRemoteConfig.getInstance())
//            }
//
//            if (!BuildConfig.DEBUG) {
//                fetchAndActivate().addOnSuccessListener {
//                    applyFirebaseConfig(FirebaseRemoteConfig.getInstance())
//                }
//            }
//        }
//    }
//
//    private fun applyFirebaseConfig(config: FirebaseRemoteConfig) {
//        baseUrl = config.getString("baseUrl")
//    }
//
//    fun getBaseUrl(): String {
//        try {
//            return this.baseUrl
//        } catch (_: UninitializedPropertyAccessException){}
//
//        return "https://ya.ru" //TODO valid real prod address(graceful degradation mode)
//    }
//}
