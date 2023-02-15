package com.mollick.photoapp.presentation.ui.image_fragment

import com.mollick.photoapp.domain.model.ImageDetail

/** State to handle API states and show details accordingly. */
data class ImageDetailState(
    val isLoading: Boolean = false,
    val data: ImageDetail? = null,
    val error: String? = null
)
