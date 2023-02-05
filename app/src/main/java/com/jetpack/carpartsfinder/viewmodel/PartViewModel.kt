package com.jetpack.carpartsfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.jetpack.carpartsfinder.dto.PartViewState
import com.jetpack.carpartsfinder.mapper.PartMapper
import com.jetpack.carpartsfinder.network.PartRepository
import com.jetpack.carpartsfinder.network.external.ExternalPartRepository
import com.jetpack.carpartsfinder.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PartViewModel @Inject constructor(
    private val partRepository: ExternalPartRepository,
    private val partMapper: PartMapper
) : ViewModel() {

    private val viewModelState = MutableStateFlow(PartViewState.Loading)
    val uiState: StateFlow<PartViewState> = viewModelState

    fun searchOnePart(uuid: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = partRepository.getPart(uuid)
                when (result) {
                    is Resource.Success -> {
                        viewModelState.value = PartViewState(
                            partData = partMapper.map(result.data!!),
                        )
                    }

                    else -> {
//                        Firebase.crashlytics.log("Can't map response to DTO. Message: ${result.message} ")
                        throw java.lang.RuntimeException("viewModelState.update: unknown result$result")
                        //TODO обработка ошибок
                    }
                }
            }
        }
    }
}
