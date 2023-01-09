package com.jetpack.carpartsfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.carpartsfinder.dto.ParData
import com.jetpack.carpartsfinder.dto.PartViewState
import com.jetpack.carpartsfinder.network.PartRepository
import com.jetpack.carpartsfinder.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PartViewModel @Inject constructor(
    private val partRepository: PartRepository
) : ViewModel() {

    private val viewModelState = MutableStateFlow(PartViewState.Loading)
    val uiState = viewModelState
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value
        )

    fun searchOnePart(uuid: String) {
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = partRepository.getPart(uuid)
            viewModelState.update {
                when (result) {

                    is Resource.Success -> {
//                        result.data?.let { responseData ->

                        if (result.data != null) {
                            val partData = ParData(
                                id = result.data.id,
                                manufacturer = result.data.manufacturer,
                                images = emptyList()
                            )
                            it.copy(partData = partData, isLoading = false)
                        } else {
                            throw java.lang.RuntimeException()
                        }
                    }
                    else -> {
                        throw java.lang.RuntimeException("viewModelState.update: unknown result$result")
//                        //TODO обработка ошибок
                    }
                }
            }
        }
    }
}
