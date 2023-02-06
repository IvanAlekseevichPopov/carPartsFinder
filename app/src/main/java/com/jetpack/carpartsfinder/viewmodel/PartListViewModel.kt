package com.jetpack.carpartsfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.carpartsfinder.dto.PartListViewState
import com.jetpack.carpartsfinder.mapper.PartListMapper
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
class PartListViewModel @Inject constructor(
    private val partRepository: ExternalPartRepository,
    private val partListMapper: PartListMapper
) : ViewModel() {

    private val viewModelState = MutableStateFlow(PartListViewState.InitialLoading)
    val uiState: StateFlow<PartListViewState> = viewModelState

    //TODO переделать работу со стейтом, по примеру partViewModel
    fun search(searchString: String?) {
        viewModelState.value = PartListViewState.InitialLoading

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = partRepository.getParts(searchString)
                when (result) {
                    is Resource.Success -> viewModelState.value = PartListViewState(
                        parts = result.data!!.map { partListMapper.mapExternal(it) },
                        inputText = searchString,
                        isLoading = false,
                    )
                    else -> {
                        throw RuntimeException("viewModelState.update: unknown result${result.message} ${result.data}")
                        //TODO обработка ошибок
                    }
                }
            }
        }
    }

    init {
        search(null)
    }
}
