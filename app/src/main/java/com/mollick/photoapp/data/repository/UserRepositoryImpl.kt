package com.mollick.photoapp.data.repository

import com.mollick.photoapp.data.remote.JsonPlaceholderApi
import com.mollick.photoapp.data.remote.dto.UserImageDto
import com.mollick.photoapp.data.remote.dto.UsersDto
import com.mollick.photoapp.domain.repository.UserRepository
import javax.inject.Inject

/** Implementation of [UserRepository] in data layer. */
class UserRepositoryImpl @Inject constructor(
    private val api: JsonPlaceholderApi
): UserRepository {
    override suspend fun getAllUsers(): List<UsersDto> {
        return api.getAllUsers()
    }

    override suspend fun getUserImageById(id: Int): UserImageDto {
        return api.getImageById(id)
    }
}
