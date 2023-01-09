package com.jetpack.carpartsfinder.network

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
    fun providesUserApi(): ApiInterface {
        val okHttpClient: OkHttpClient?
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://dev-54ta5gq-snpoahtd2a2hk.fr-3.platformsh.site")
//            .baseUrl("https://639edbc67aaf11ceb88c81e1.mockapi.io")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiInterface::class.java)
    }
}












