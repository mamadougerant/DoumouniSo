package com.malisoftware.local.reamlModel

import com.malisoftware.model.format.formatDeliveryFee
import com.malisoftware.model.format.formatDeliveryTime
import com.malisoftware.model.format.formatMinPrice
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class RealmBusiness : EmbeddedRealmObject {
    var restaurantId: String = "0"
    var title: String = "title"
    var category: String = "category"
    var minPrice: Double = 0.0
    var formattedMinPrice: String = formatMinPrice(minPrice)
    var description: String = "description"
    var feedback: String = "4.4"
    var imageUrl: String = ""
    var promotion: String = "10% reduction"
    var promotionDescription: String = "10% reduction on all orders min 10€"
    var deliveryTime: Int = 10
    var formattedDeliveryTime: String = formatDeliveryTime(deliveryTime)
    var deliveryFee: Double = 2000.00
    var formattedDeliveryFee: String = formatDeliveryFee(deliveryFee)
    var isOpen: Boolean = true
    var openingTime: String = "TODO CHECK IT"
    var closingTime: String = ""
    var openingDays: RealmList<String> = realmListOf()
    var raterCount: Long = 5000
    var goodRate: Long = 5000
    var badRate: Long = 0
    var orderCount: Long = 5000
    var sponsored: Boolean = false
    var isRestaurant: Boolean = true
}