package com.jetpack.carpartsfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.carpartsfinder.network.PartRepository
import com.jetpack.carpartsfinder.network.PartResponse
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

    private val viewModelState = MutableStateFlow(PartListViewState.Loading)
    val uiState = viewModelState
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value
        )

    fun search(searchString: String?) {
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = partRepository.getParts(searchString)
            viewModelState.update {
                when (result) {
                    is Resource.Success -> it.copy(parts = result.data!!, isLoading = false)
                    else -> {
                        throw java.lang.RuntimeException("viewModelState.update: unknown result$result")
//                        //TODO обработка ошибок
                    }
                }
            }
        }
    }

    init {
        search(null)
    }

    data class PartListViewState(
        val parts: List<PartResponse>,
        val isLoading: Boolean,
    ) {
        companion object {
            val Loading = PartListViewState(
                parts = emptyList(),
                isLoading = true,
            )
        }
    }
}
