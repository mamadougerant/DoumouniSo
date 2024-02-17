package com.malisoftware.ai.model.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Unit(
    @SerialName("type")
    val type: String,
    @SerialName("enum")
    val `enum`: List<String>
)