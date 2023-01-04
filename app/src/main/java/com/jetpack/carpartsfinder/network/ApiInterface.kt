package com.jetpack.carpartsfinder.network

import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ApiInterface {
    @GET("/parts")
    suspend fun getParts(@Query("searchString") searchString: String?): List<PartResponse>
}
