package com.jetpack.carpartsfinder.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.jetpack.carpartsfinder.mapper.PartListMapper
import com.jetpack.carpartsfinder.mapper.PartMapper
import com.jetpack.carpartsfinder.util.RemoteConfigInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiService {
    companion object {
        private const val READ_TIMEOUT = 5L
        private const val CONNECT_TIMEOUT = 5L
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun providesUserApi(
         config: RemoteConfigInterface
    ): ApiInterface {
        val okHttpClient: OkHttpClient?
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
//            .addInterceptor(getServerErrorHandler())
            .build()

        return Retrofit.Builder()
            .baseUrl(config.getBaseUrl())
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
            .create(ApiInterface::class.java)
    }
}












