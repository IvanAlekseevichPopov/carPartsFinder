package com.jetpack.carpartsfinder.util

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.jetpack.carpartsfinder.BuildConfig
import com.jetpack.carpartsfinder.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Singleton

private const val FETCH_INTERVAL_IN_SECONDS = 60 * 30L

@InstallIn(SingletonComponent::class)
@Module
class RemoteConfig: RemoteConfigInterface {
    private val configReady = MutableStateFlow(false)
    override val isConfigReady: StateFlow<Boolean> = configReady

    private lateinit var baseUrl: String
    private lateinit var imagesBaseUrl: String

    @Singleton
    @Provides
    fun provideRemoteConfig(): RemoteConfigInterface {
        val prod = !BuildConfig.DEBUG

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
                if(!prod) {
                    configReady.value = true
                }
            }

            if (prod) {
                fetchAndActivate().addOnSuccessListener {
                    applyFirebaseConfig(FirebaseRemoteConfig.getInstance())
                    configReady.value = true
                }
            }

            FirebaseRemoteConfig.getInstance().fetchAndActivate()
        }

        return this
    }

    private fun applyFirebaseConfig(config: FirebaseRemoteConfig) {
        baseUrl = config.getString("baseUrl")
        imagesBaseUrl = config.getString("imagesBaseUrl")
    }

    override fun getBaseUrl(): String {
        return this.baseUrl
    }

    override fun getImagesBaseUrl(): String {
        return this.imagesBaseUrl
    }


}
