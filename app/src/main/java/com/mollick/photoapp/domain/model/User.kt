package com.mollick.photoapp.domain.model

import com.mollick.photoapp.data.remote.dto.Address

/** Model class to reduce memory with sufficient information for UserList screen. */
data class User(
    val id: Int,
    val name: String,
    val city: String,
    val address: Address
)
