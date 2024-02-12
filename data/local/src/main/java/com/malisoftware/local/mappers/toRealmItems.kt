package com.malisoftware.local.mappers

import com.malisoftware.local.local.ItemsEntity
import com.malisoftware.local.reamlModel.RealmItems


fun ItemsEntity.toRealmItems(): RealmItems {
    return RealmItems().apply {
        restaurantId = this@toRealmItems.restaurantId
        title = this@toRealmItems.title
        price = this@toRealmItems.price
        description = this@toRealmItems.description
        feedback = this@toRealmItems.feedback
        imageUrl = this@toRealmItems.imageUrl
        promotion = this@toRealmItems.promotion
        quantity = this@toRealmItems.quantity
        category = this@toRealmItems.category
        rate = this@toRealmItems.rate
        raterCount = this@toRealmItems.raterCount
        goodRate = this@toRealmItems.goodRate
        badRate = this@toRealmItems.badRate
        verified = this@toRealmItems.verified
        formattedPrice = this@toRealmItems.formattedPrice
    }
}