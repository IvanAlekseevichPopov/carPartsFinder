package com.jetpack.carpartsfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.carpartsfinder.dto.ParData
import com.jetpack.carpartsfinder.dto.PartViewState
import com.jetpack.carpartsfinder.network.PartRepository
import com.jetpack.carpartsfinder.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PartViewModel @Inject constructor(
    private val partRepository: PartRepository
) : ViewModel() {

    private val viewModelState = MutableStateFlow(PartViewState.Loading)
    val uiState: StateFlow<PartViewState> = viewModelState

    fun searchOnePart(uuid: String) {
        viewModelScope.launch {
            val result = partRepository.getPart(uuid)
            when (result) {
                is Resource.Success -> {
                    //TODO перенести в маппер
                    val partData = ParData(
                        id = result.data!!.id,
                        partNumber = result.data.partNumber,
                        manufacturer = result.data.manufacturer,
                        images = listOf()
                    )

                    viewModelState.value = PartViewState(
                        partData = partData
                    )
                }
                else -> {
                    throw java.lang.RuntimeException("viewModelState.update: unknown result$result")
//                        //TODO обработка ошибок
                }
            }
//            }
        }
    }
}
