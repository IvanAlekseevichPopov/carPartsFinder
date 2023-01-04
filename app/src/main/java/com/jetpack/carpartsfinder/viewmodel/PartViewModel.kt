package com.jetpack.carpartsfinder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpack.carpartsfinder.network.PartRepository
import com.jetpack.carpartsfinder.network.PartResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PartViewModel @Inject constructor(
    private val partRepository: PartRepository
) : ViewModel() {

    private val _state = MutableLiveData<List<PartResponse>?>()
    val state: LiveData<List<PartResponse>?>
        get() = _state

    fun beginSearch(searchString: String?) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val result = partRepository.getParts(searchString)

                _state.postValue(result.data)
            }
        }
    }

    init {
        beginSearch(null)
    }
}
