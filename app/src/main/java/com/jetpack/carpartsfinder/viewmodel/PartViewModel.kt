package com.jetpack.carpartsfinder.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jetpack.carpartsfinder.model.Part
import com.jetpack.carpartsfinder.repository.PartRepository
import com.jetpack.carpartsfinder.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PartViewModel @Inject constructor(
    private val partRepository: PartRepository
): ViewModel() {

    var isLoading = mutableStateOf(false)
    private var _getPartsData: MutableLiveData<List<Part>> = MutableLiveData<List<Part>>()
    var getPartsData: LiveData<List<Part>> = _getPartsData

    suspend fun getPartsData(): Resource<List<Part>> {
        val result = partRepository.getParts()
        if (result is Resource.Success) {
            isLoading.value = true
            _getPartsData.value = result.data!!
        }

        return result
    }
}