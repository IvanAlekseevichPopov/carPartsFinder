package com.jetpack.carpartsfinder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.carpartsfinder.dto.SinglePartViewState
import com.jetpack.carpartsfinder.network.PartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject

@HiltViewModel
class PartViewModel @Inject constructor(
    private val partRepository: PartRepository,
) : ViewModel(), UIPartViewModel {
    private val _partDataState = MutableStateFlow<SinglePartViewState>(SinglePartViewState.Loading)
    override val partDataState = _partDataState.asStateFlow()

    fun searchOnePart(uuid: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _partDataState.value = SinglePartViewState.Loading
                try {
                    _partDataState.value = SinglePartViewState.Loaded(partRepository.getPart(uuid))
                } catch (e: HttpException) {
                    if(e.code() == HttpURLConnection.HTTP_NOT_FOUND) {
                        //TODO send critical to crashlytics
                        _partDataState.value = SinglePartViewState.NotFound
                    } else if (e.code() == HttpURLConnection.HTTP_INTERNAL_ERROR) {
                        //TODO send something to crashlytics
                        _partDataState.value = SinglePartViewState.ServerError
                    }
                }
            }
        }
    }

    override fun triggerImageClick(url: String) {

        _partDataState.value = SinglePartViewState.ImageZoomed(url)

//        viewModelScope.launch(Dispatchers.IO){
//            partDataState.collectLatest {
//                Log.d("!!!", "changed uiState")
//            }
//        }
//
//        viewModelScope.launch(Dispatchers.IO){
//            zoomedImage.collectLatest {
//                Log.d("!!!", "changed zoomedImage")
//            }
//        }
    }

    override fun triggerImageHide() {
//        TODO
//        _partDataState.value = SinglePartViewState.Loaded()
    }
}
