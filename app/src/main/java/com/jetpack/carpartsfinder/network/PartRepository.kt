package com.jetpack.carpartsfinder.network

import com.jetpack.carpartsfinder.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class PartRepository @Inject constructor(
    private val apiInterface: ApiInterface
) {
    suspend fun getParts(searchString: String?): Resource<List<PartResponse>> {
        val response = try {
            apiInterface.getParts(searchString)
        } catch (e: Exception) {
            //TODO обработка ошибок
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }

    suspend fun getPart(uuid: String): Resource<SinglePartResponse> {
        val response = try {
            apiInterface.getPart(uuid)
        } catch (e: Exception) {
            //TODO обработка ошибок общая
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }
}
