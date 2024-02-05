package com.malisoftware.local.mappers

import com.malisoftware.local.local.BusinessEntity
import com.malisoftware.model.BusinessData

fun BusinessData.toBusinessEntity () = BusinessEntity(
    restaurantId = id,
    title = title,
    category = category,
    minPrice = minPrice,
    description = description,
    feedback = feedback,
    imageUrl = imageUrl,
    promotion = promotion,
    promotionDescription = promotionDescription,
    deliveryTime = deliveryTime,
    deliveryFee = deliveryFee,
    isOpen = isOpen,
    openingTime = openingTime,
    closingTime = closingTime,
    openingDays = openingDays,
    raterCount = raterCount,
    goodRate = goodRate,
    badRate = badRate,
    orderCount = orderCount,
    sponsored = sponsored,
    isRestaurant = isRestaurant
)