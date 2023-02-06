package com.jetpack.carpartsfinder.network.external

import android.util.Log
import com.jetpack.carpartsfinder.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject

@ActivityScoped
class ExternalPartRepository @Inject constructor(
    private val apiInterface: ExternalApiInterface
) {
    private val requests = ConcurrentHashMap<String, ExternalPartResponse>()

    suspend fun getParts(searchString: String?): Resource<List<ExternalPartResponse>> {
        val search = if (searchString.isNullOrBlank()) "ST39680SHJA61" else searchString

        val response = try {
            apiInterface.getParts(search)
        } catch (e: Exception) {
            //TODO обработка ошибок
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }

        response.forEach {
            requests.put(it.partNumber, it)
        }
        return Resource.Success(response)
    }

    suspend fun getPart(id: String): Resource<ExternalSinglePartResponse> {
        var part = requests.getOrDefault(id, null)
        if (part == null) {
            Log.d("!!!", "Part data not found in cache, requesting from server")
            part = getParts(id).data?.first { it.partNumber == id }
            if (part == null) {
                return Resource.Error("Part data not found on server")
            }
        }

        val imageData = try {
            apiInterface.getPart(part.manufacturerId.toString(), part.partNumber)
        } catch (e: Exception) {
            //TODO обработка ошибок
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }

        return Resource.Success(ExternalSinglePartResponse(part, imageData.images))
    }
}
