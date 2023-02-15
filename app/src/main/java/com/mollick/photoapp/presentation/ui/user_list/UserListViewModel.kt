package com.mollick.photoapp.presentation.ui.user_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mollick.photoapp.common.Resource
import com.mollick.photoapp.domain.use_cases.get_users.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/** ViewModel to observe state changes on different orientation. */
@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
): ViewModel() {
    private var _state = MutableLiveData(UserListState())
    val state: LiveData<UserListState> = _state

    init {
        viewModelScope.launch { initLiveData() }
    }

    private suspend fun initLiveData() {
            getUsersUseCase().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = UserListState(data = result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        _state.value = UserListState(error = result.message)
                    }
                    is Resource.Loading -> {
                        _state.value = UserListState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }
}
