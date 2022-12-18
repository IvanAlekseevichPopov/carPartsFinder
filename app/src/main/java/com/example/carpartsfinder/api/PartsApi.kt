package com.example.carpartsfinder.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PartsApi {
    @GET("/parts")
    suspend fun getParts(): Response<List<Part>>
}

object RetrofitHelper {

    val baseUrl = "https://639edbc67aaf11ceb88c81e1.mockapi.io"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}