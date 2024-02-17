package com.malisoftware.ai.model

import com.malisoftware.ai.model.response.ResponseObject
import kotlinx.serialization.Serializable

@Serializable
data class Chat(
    val id: Long = 0L,
    val message: String = "",
    val orderInfo: ResponseObject? = null,
    val isAssistant: Boolean = false,
    val time: String = "",
)
