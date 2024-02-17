package com.malisoftware.ai.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseObject(
    val foodCategory: String = "",
    val foodName: String = "",
    val mealId: String = "",
    val foodQuantity: String = "",
    val restaurantName: String = ""
)
