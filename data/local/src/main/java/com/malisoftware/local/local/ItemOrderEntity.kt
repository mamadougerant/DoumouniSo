package com.malisoftware.local.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

enum class OrderStatus(val value: String) {
    PENDING("pending"),
    ACCEPTED("accepted"),
    REJECTED("rejected"),
    DELIVERED("delivered"),
    CANCELLED("cancelled"),
    UNKNOWN("unknown")
}



@Entity
data class ItemOrderEntity(
    @Embedded
    val restaurant: BusinessEntity = BusinessEntity(),
    @PrimaryKey
    val id: String = restaurant.restaurantId,
    val specialInstruction: String = "",
    val dateTime: String = "",
    val status: OrderStatus = OrderStatus.UNKNOWN,
)
