package com.mollick.photoapp.presentation.ui.user_list

import com.mollick.photoapp.domain.model.User

/** State to handle different API states and show user list. */
data class UserListState(
    val isLoading: Boolean = false,
    val data: List<User> = emptyList(),
    val error: String? = null
)
