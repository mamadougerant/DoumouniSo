package com.malisoftware.ai.model.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName("type")
    val type: String,
    @SerialName("description")
    val description: String
)