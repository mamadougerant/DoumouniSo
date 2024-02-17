package com.malisoftware.ai.presentation

import com.malisoftware.ai.model.response.ResponseObject
import com.malisoftware.model.BusinessData

fun toolParser (
    orderInfo: ResponseObject? = null,
    restaurants: List<BusinessData> = emptyList(),
): List<BusinessData>? {
    if (orderInfo == null) return null

    return restaurants.filter {
        it.category == orderInfo.foodCategory
    }

}