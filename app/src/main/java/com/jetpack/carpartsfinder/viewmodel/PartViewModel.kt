package com.jetpack.carpartsfinder.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jetpack.carpartsfinder.model.Part
import com.jetpack.carpartsfinder.repository.PartRepository
import com.jetpack.carpartsfinder.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PartViewModel @Inject constructor(
    private val partRepository: PartRepository
): ViewModel() {
        //TODO реализовать сначала liveData потом Flow

    //TODO срисовать условия с ^^^
    val data: LiveData<List<Part>> = liveData {

        val result = partRepository.getParts()
        if(result is Resource.Success && result.data != null) {
            emit(result.data)
        } else {
            Log.d("11111", result.toString())
            //TODO обработка ошибок
        }

    }
}