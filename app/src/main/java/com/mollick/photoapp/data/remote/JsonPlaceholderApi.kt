package com.mollick.photoapp.data.remote

import com.mollick.photoapp.common.Constants.BASE_URL
import com.mollick.photoapp.data.remote.dto.UserImageDto
import com.mollick.photoapp.data.remote.dto.UsersDto
import retrofit2.http.GET
import retrofit2.http.Path

/** Retrofit API with base url [BASE_URL]. */
interface JsonPlaceholderApi {

    /** Returns list of User data. */
    @GET("/users")
    suspend fun getAllUsers(): List<UsersDto>

    /** Returns [UserImageDto] according to item [id]. */
    @GET("/photos/{id}")
    suspend fun getImageById(@Path("id") id: Int): UserImageDto
}