package com.jetpack.carpartsfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.carpartsfinder.dto.PartListViewState
import com.jetpack.carpartsfinder.dto.State
import com.jetpack.carpartsfinder.network.NetworkException
import com.jetpack.carpartsfinder.network.PartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PartListViewModel @Inject constructor(
    private val partRepository: PartRepository,
) : ViewModel(), UiPartListViewModel {

    private val viewModelState = MutableStateFlow(PartListViewState.Loading)
    override val uiState: StateFlow<PartListViewState> = viewModelState

    override fun search(searchString: String?) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                viewModelState.value = PartListViewState.Loading

                try {
                    val parts = partRepository.getParts(searchString)
                    viewModelState.value = PartListViewState(
                        parts = parts,
                        inputText = searchString,
                        state = State.Loaded,
                    )
                } catch (e: NetworkException) {
                    viewModelState.value = PartListViewState.ServerError
                }
            }
        }
    }

    init {
        search(null)
    }
}
