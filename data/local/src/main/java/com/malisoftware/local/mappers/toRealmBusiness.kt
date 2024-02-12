package com.malisoftware.local.mappers

import com.malisoftware.local.local.BusinessEntity
import com.malisoftware.local.reamlModel.RealmBusiness

fun BusinessEntity.toRealmBusiness(): RealmBusiness {
    return RealmBusiness().apply {
        restaurantId = this@toRealmBusiness.restaurantId
        title = this@toRealmBusiness.title
        category = this@toRealmBusiness.category
        minPrice = this@toRealmBusiness.minPrice
        formattedMinPrice = this@toRealmBusiness.formattedMinPrice
        description = this@toRealmBusiness.description
        feedback = this@toRealmBusiness.feedback
        imageUrl = this@toRealmBusiness.imageUrl
        promotion = this@toRealmBusiness.promotion
        promotionDescription = this@toRealmBusiness.promotionDescription
        deliveryTime = this@toRealmBusiness.deliveryTime
        formattedDeliveryTime = this@toRealmBusiness.formattedDeliveryTime
        deliveryFee = this@toRealmBusiness.deliveryFee
        formattedDeliveryFee = this@toRealmBusiness.formattedDeliveryFee
        isOpen = this@toRealmBusiness.isOpen
        openingTime = this@toRealmBusiness.openingTime
        closingTime = this@toRealmBusiness.closingTime
        raterCount = this@toRealmBusiness.raterCount
        goodRate = this@toRealmBusiness.goodRate
        badRate = this@toRealmBusiness.badRate
        orderCount = this@toRealmBusiness.orderCount
        sponsored = this@toRealmBusiness.sponsored
        isRestaurant = this@toRealmBusiness.isRestaurant
    }
}

fun RealmBusiness.toBusinessEntity(): BusinessEntity {
    return BusinessEntity(
        restaurantId = this.restaurantId,
        title = this.title,
        category = this.category,
        minPrice = this.minPrice,
        formattedMinPrice = this.formattedMinPrice,
        description = this.description,
        feedback = this.feedback,
        imageUrl = this.imageUrl,
        promotion = this.promotion,
        promotionDescription = this.promotionDescription,
        deliveryTime = this.deliveryTime,
        formattedDeliveryTime = this.formattedDeliveryTime,
        deliveryFee = this.deliveryFee,
        formattedDeliveryFee = this.formattedDeliveryFee,
        isOpen = this.isOpen,
        openingTime = this.openingTime,
        closingTime = this.closingTime,
        raterCount = this.raterCount,
        goodRate = this.goodRate,
        badRate = this.badRate,
        orderCount = this.orderCount,
        sponsored = this.sponsored,
        isRestaurant = this.isRestaurant
    )
}


