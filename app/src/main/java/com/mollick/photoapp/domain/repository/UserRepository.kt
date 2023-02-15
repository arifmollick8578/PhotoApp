package com.mollick.photoapp.domain.repository

import com.mollick.photoapp.data.remote.dto.UserImageDto
import com.mollick.photoapp.data.remote.dto.UsersDto

/** Repository to fetch data from domain layer. */
interface UserRepository {
    suspend fun getAllUsers(): List<UsersDto>

    suspend fun getUserImageById(id: Int): UserImageDto
}
