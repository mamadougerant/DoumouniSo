package com.malisoftware.local.mappers

import com.malisoftware.local.local.ItemOrderEntity
import com.malisoftware.local.local.ItemsEntity
import com.malisoftware.model.BusinessData
import com.malisoftware.model.Items

fun Items.toItemEntity () = ItemsEntity(
    itemId = id,
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