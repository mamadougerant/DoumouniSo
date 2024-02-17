package com.malisoftware.ai.model.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Message(
    @SerialName("role")
    val role: String,
    @SerialName("content")
    val content: String
)