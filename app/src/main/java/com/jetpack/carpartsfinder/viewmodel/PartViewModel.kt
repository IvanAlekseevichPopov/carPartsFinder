package com.jetpack.carpartsfinder.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.carpartsfinder.dto.PartData
import com.jetpack.carpartsfinder.network.NotFoundException
import com.jetpack.carpartsfinder.network.PartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject

@HiltViewModel
class PartViewModel @Inject constructor(
    private val partRepository: PartRepository,
) : ViewModel(), UIPartViewModel {
    private val _partDataState = MutableStateFlow<PartData?>(null)
    override val partDataState = _partDataState.asStateFlow()

    private val _zoomedImage = MutableStateFlow<String?>(null)
    override val zoomedImage = _zoomedImage.asStateFlow()

    fun searchOnePart(uuid: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    _partDataState.value = partRepository.getPart(uuid)
                } catch (e: NotFoundException) {
                    _partDataState.value = null
                } catch (e: HttpException) {
                    if(e.code() == HttpURLConnection.HTTP_NOT_FOUND) {
                        _partDataState.value = null
                    } else if (e.code() == HttpURLConnection.HTTP_INTERNAL_ERROR) {
                        _partDataState.value = null
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
