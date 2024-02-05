package com.malisoftware.model



data class Address(
    val city: String? = null,
    val country: String? = null,
    val line1: String? = null,
    val line2: String? = null,
    val lat: Double? = null,
    val lng: Double? = null,
    val postalCode: String? = null,
    val state: String? = null
){
    constructor() : this(null, null, null, null, null, null)
}
