package com.malisoftware.local.mappers

import com.malisoftware.local.local.ItemOrderEntity
import com.malisoftware.local.local.ItemsEntity
import com.malisoftware.model.BusinessData
import com.malisoftware.model.Items

fun Items.toItemEntity (restaurantId: String) = ItemsEntity(
    itemId = id,
    title = title,
    restaurantId = restaurantId,
    description = description,
    price = price,
    imageUrl = imageUrl,
    category = category,
    promotion = promotion,
    quantity = quantity,
    rate = rate,
    raterCount = raterCount,
    goodRate = goodRate,
    badRate = badRate,
    verified = verified,
)

fun ItemsEntity.toItems () = Items(
    id = itemId,
    title = title,
    description = description,
    price = price,
    imageUrl = imageUrl,
    category = category,
    promotion = promotion,
    quantity = quantity,
    rate = rate,
    raterCount = raterCount,
    goodRate = goodRate,
    badRate = badRate,
    verified = verified,
)