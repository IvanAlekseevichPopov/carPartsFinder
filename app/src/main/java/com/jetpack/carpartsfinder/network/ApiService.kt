package com.jetpack.carpartsfinder.network

import com.jetpack.carpartsfinder.repository.PartRepository
import com.jetpack.carpartsfinder.repository.UserRespository
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

    @Singleton
    @Provides
    fun provideUserRepository(
        api: ApiInterface
    ) = UserRespository(api)

    @Singleton
    @Provides
    fun providePartRepository(
        api: ApiInterface
    ) = PartRepository(api)

    @Singleton
    @Provides
    fun providesUserApi(): ApiInterface {
        var okHttpClient: OkHttpClient? = null
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://639edbc67aaf11ceb88c81e1.mockapi.io")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiInterface::class.java)
    }
}












