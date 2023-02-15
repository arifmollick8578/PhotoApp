package com.mollick.photoapp.common

/** Sealed class to handle retrofit request status.
 *
 * @param data response data.
 * @param message response code/ error message.
 **/
sealed class Resource<T>(val data: T? = null, val message: String? = null) {

    /** Data class to handle Retrofit request success scenario. */
    class Success<T>(data: T) : Resource<T>(data)

    /** Data class to handle Retrofit request error scenario. */
    class Error<T>(data: T? = null, message: String) : Resource<T>(data, message)

    /** Data class to handle Retrofit request loading scenario. */
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
