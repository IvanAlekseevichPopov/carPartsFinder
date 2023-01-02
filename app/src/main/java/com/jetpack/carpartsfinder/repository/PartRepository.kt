package com.jetpack.carpartsfinder.repository

import com.jetpack.carpartsfinder.model.Part
import com.jetpack.carpartsfinder.network.ApiInterface
import com.jetpack.carpartsfinder.utils.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class PartRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun getParts(): Resource<List<Part>> {
        val response = try {
            apiInterface.getParts()
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }
}