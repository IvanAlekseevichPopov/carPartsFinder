package com.jetpack.carpartsfinder.network.external

import com.jetpack.carpartsfinder.network.ApiInterface
import com.jetpack.carpartsfinder.network.PartResponse
import com.jetpack.carpartsfinder.network.SinglePartResponse
import com.jetpack.carpartsfinder.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class ExternalPartRepository @Inject constructor(
    private val apiInterface: ExternalApiInterface
) {
    suspend fun getParts(searchString: String?): Resource<List<ExternalPartResponse>> {
        val search = if (searchString.isNullOrBlank()) "ST39680SHJA61" else searchString

        val response = try {
            apiInterface.getParts(search)
        } catch (e: Exception) {
            //TODO обработка ошибок
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }

    suspend fun getPart(id: String): Resource<SinglePartResponse> {
        val response = try {
            apiInterface.getPart(id)
        } catch (e: Exception) {
            //TODO обработка ошибок общая
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }

        return Resource.Success(response)
    }
}
