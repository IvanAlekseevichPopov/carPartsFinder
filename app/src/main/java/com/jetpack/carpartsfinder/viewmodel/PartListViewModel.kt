package com.jetpack.carpartsfinder.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.carpartsfinder.dto.PartListViewState
import com.jetpack.carpartsfinder.network.PartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject

@HiltViewModel
class PartListViewModel @Inject constructor(
    private val partRepository: PartRepository,
) : ViewModel(), UiPartListViewModel {

    private val viewModelState = MutableStateFlow<PartListViewState>(PartListViewState.Loading())
    override val uiState: StateFlow<PartListViewState> = viewModelState

    override fun search(searchString: String?) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                viewModelState.value = PartListViewState.Loading(searchString)

                try {
                    val parts = partRepository.getParts(searchString)
                    Log.d("!!!", "count: ${parts.size}")
                    viewModelState.value = PartListViewState.Loaded(parts, searchString)
                } catch (e: HttpException) {
                    when (e.code()) {
                        HttpURLConnection.HTTP_SERVER_ERROR,
                        HttpURLConnection.HTTP_BAD_GATEWAY,
                        HttpURLConnection.HTTP_CLIENT_TIMEOUT,
                        HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> {
                            viewModelState.value = PartListViewState.ServerError(searchString)
                        }
                    }
                    viewModelState.value = PartListViewState.ServerError(searchString)
                }
            }
        }
    }

    init {
        search(null)
    }
}
