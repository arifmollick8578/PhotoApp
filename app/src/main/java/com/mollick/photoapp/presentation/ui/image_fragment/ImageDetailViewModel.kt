package com.mollick.photoapp.presentation.ui.image_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mollick.photoapp.common.Resource
import com.mollick.photoapp.domain.use_cases.get_image.GetImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/** ViewModel to observe state changes on different orientation. */
@HiltViewModel
class ImageDetailViewModel @Inject constructor(
    private val getImageDetail: GetImageUseCase
): ViewModel() {
    private var _state = MutableLiveData(ImageDetailState())
    val state: LiveData<ImageDetailState> = _state

    fun getImageDetailById(id: Int) {
        viewModelScope.launch {
            getImageDetail(id).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = ImageDetailState(data = result.data)
                    }
                    is Resource.Error -> {
                        _state.value = ImageDetailState(error = result.message)
                    }
                    is Resource.Loading -> {
                        _state.value = ImageDetailState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}