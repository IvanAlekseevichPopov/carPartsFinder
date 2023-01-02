package com.jetpack.carpartsfinder.network

import com.jetpack.carpartsfinder.model.Part
import com.jetpack.carpartsfinder.model.UserResponse
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ApiInterface {

    @GET("todos")
    suspend fun getUserData(): List<UserResponse>

    @GET("/parts")
    suspend fun getParts(): List<Part>
}