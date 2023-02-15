package com.mollick.photoapp.data.remote.dto

/** Data class to store Address from API response. */
data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo
) {
    override fun toString(): String {
        return "Address: $street, $suite, $city - $zipcode \n(Lat: ${geo.lat}, Long: ${geo.lng})"
    }
}
