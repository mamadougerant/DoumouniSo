package com.malisoftware.local.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

enum class OrderStatus() {
    PENDING,
    ACCEPTED,
    REJECTED,
    DELIVERED,
    CANCELLED,
    UNKNOWN
}

@Entity
data class ItemOrderEntity(
    @Embedded
    val restaurant: BusinessEntity = BusinessEntity(),
    @PrimaryKey()
    val id: String = restaurant.restaurantId,
    //val customer: UserData = UserData(),
    val items: List<ItemsEntity> = listOf(),
    val specialInstruction: String = "",
    val dateTime: String = "",
    val status: OrderStatus = OrderStatus.UNKNOWN,
)
