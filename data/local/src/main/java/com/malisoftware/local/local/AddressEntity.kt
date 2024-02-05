package com.malisoftware.local.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class AddressEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val city: String? = null,
    val country: String? = null,
    val line1: String? = null,
    val line2: String? = null,
    val lat: Double? = null,
    val lng: Double? = null,
    val postalCode: String? = null,
    val state: String? = null
){
    constructor() : this(0,null, null, null, null, null, null)
}
