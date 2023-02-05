package com.jetpack.carpartsfinder.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ApiInterface {
    @GET("/api/parts")
    suspend fun getParts(@Query("searchString") searchString: String?): List<PartResponse>

    @GET("/api/parts/{id}")
    suspend fun getPart(@Path("id") id: String): SinglePartResponse
}
