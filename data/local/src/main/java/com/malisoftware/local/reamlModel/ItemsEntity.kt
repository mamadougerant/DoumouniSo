package com.malisoftware.local.reamlModel

import com.malisoftware.local.local.ItemsEntity
import com.malisoftware.model.format.formatPrice
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class RealmItems : RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var restaurantId: String = ""
    var title: String = "title"
    var price: Double = 1110.0
    var description: String = "description"
    var feedback: String = ""
    var imageUrl: String = ""
    var promotion: String = "10% reduction"
    var quantity: Int = 0
    var category: String = "category"
    var rate: Double = 3.9
    var raterCount: Long = 5000
    var goodRate: Long = 5000
    var badRate: Long = 0
    var verified: Boolean = false
    var formattedPrice: String = formatPrice(price)
}
