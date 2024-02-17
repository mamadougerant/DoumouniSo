package com.malisoftware.ai.model.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Parameters(
    @SerialName("type")
    val type: String,
    @SerialName("properties")
    val properties: Properties,
    @SerialName("required")
    val required: List<String>
)