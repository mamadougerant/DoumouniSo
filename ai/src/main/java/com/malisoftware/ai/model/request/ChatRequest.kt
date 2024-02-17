package com.malisoftware.ai.model.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatRequest(
    @SerialName("model")
    val model: String = "",
    @SerialName("messages")
    val messages: List<Message> = listOf(),
    @SerialName("tools")
    val tools: List<Tool> = listOf(),
    @SerialName("tool_choice")
    val tool_choice: String = "",
)

