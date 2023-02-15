package com.mollick.photoapp.data.remote.dto

import com.mollick.photoapp.domain.model.User

/** Data Transfer Object to transfer data into data class from JSON. */
data class UsersDto(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
)

/** Convert DTO into smaller and sufficient object. */
fun UsersDto.toUser(): User {
    return User(
        id = id,
        name = name,
        city = address.city,
        address = address
    )
}
