package com.mollick.photoapp.data.remote.dto

import com.mollick.photoapp.domain.model.ImageDetail

/** Data Transfer Object to transfer data into data class from JSON. */
data class UserImageDto(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)

/** Convert DTO into smaller and sufficient object. */
fun UserImageDto.toImageDetail(): ImageDetail {
    return ImageDetail(title, url)
}
