package com.jetpack.carpartsfinder.network.external

import com.jetpack.carpartsfinder.network.PartResponse
import com.jetpack.carpartsfinder.network.SinglePartResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ExternalApiInterface {
    @GET("/api/manufacturers/{searchString}?showAll=false")
    suspend fun getParts(@Path("searchString") searchString: String): List<ExternalPartResponse>

    @GET("/api/parts/{id}")
    suspend fun getPart(@Path("id") id: String): SinglePartResponse
}
