package com.malisoftware.ai.model.request


import com.malisoftware.ai.model.request.Function
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tool(
    @SerialName("type")
    val type: String,
    @SerialName("function")
    val function: Function
)