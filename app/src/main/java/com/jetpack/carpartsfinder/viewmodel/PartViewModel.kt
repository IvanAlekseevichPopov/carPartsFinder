package com.jetpack.carpartsfinder.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.carpartsfinder.dto.PartData
import com.jetpack.carpartsfinder.mapper.PartMapper
import com.jetpack.carpartsfinder.network.external.ExternalPartRepository
import com.jetpack.carpartsfinder.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PartViewModel @Inject constructor(
    private val partRepository: ExternalPartRepository,
    private val partMapper: PartMapper
) : ViewModel(), UIPartViewModel {
    private val _partDataState = MutableStateFlow<PartData?>(null)
    override val partDataState = _partDataState.asStateFlow()

    private val _zoomedImage = MutableStateFlow<String?>(null)
    override val zoomedImage = _zoomedImage.asStateFlow()

    fun searchOnePart(uuid: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = partRepository.getPart(uuid)
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            _partDataState.value = partMapper.mapExternal(it)
                        } ?: run {
                            throw RuntimeException("viewModelState.update: result.data is null")
                        }
                    }

                    else -> {
//                        Firebase.crashlytics.log("Can't map response to DTO. Message: ${result.message} ")
                        throw RuntimeException("viewModelState.update: unknown result$result")
                    }
                }
            }
        }
    }

    override fun triggerImageClick(url: String) {
        _zoomedImage.value = url

        viewModelScope.launch(Dispatchers.IO){
            partDataState.collectLatest {
                Log.d("!!!", "changed uiState")
            }
        }

        viewModelScope.launch(Dispatchers.IO){
            zoomedImage.collectLatest {
                Log.d("!!!", "changed zoomedImage")
            }
        }
    }

    override fun triggerImageHide() {
        _zoomedImage.value = null
    }
}
