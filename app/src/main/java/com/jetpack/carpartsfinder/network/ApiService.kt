package com.jetpack.carpartsfinder.network

import android.util.Log
import com.jetpack.carpartsfinder.utils.RemoteConfigInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiService {
    companion object {
        private const val READ_TIMEOUT = 10L
        private const val CONNECT_TIMEOUT = 10L
    }

//    @Singleton
//    @Provides
//    fun provideUserRepository(
//        api: ApiInterface
//    ) = UserRespository(api)

    @Singleton
    @Provides
    fun providePartRepository(
        api: ApiInterface
    ) = PartRepository(api)

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
            .build()

//        Log.d("!!!", config.getBaseUrl())
        return Retrofit.Builder()
//            .baseUrl(RemoteConfig.getBaseUrl())
            .baseUrl("https://dev-54ta5gq-snpoahtd2a2hk.fr-3.platformsh.site")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiInterface::class.java)
    }
}












