package com.malisoftware.local.local

import androidx.room.Entity
import com.malisoftware.model.format.formatDeliveryFee
import com.malisoftware.model.format.formatDeliveryTime
import com.malisoftware.model.format.formatMinPrice
import com.malisoftware.model.format.formatPrice


data class BusinessEntity(
    val restaurantId: String = "0",
    val title: String = "title",
    val category: String = "category",
    val minPrice: Double = 0.0,
    val formattedMinPrice: String = formatMinPrice(minPrice),
    val description: String = "description",
    val feedback: String = "4.4",
    val imageUrl: String = "",
    val promotion: String = "10% reduction",
    val promotionDescription: String = "10% reduction on all orders min 10€",
    val deliveryTime: Int = 10,
    val formattedDeliveryTime: String = formatDeliveryTime(deliveryTime),
    val deliveryFee: Double = 2000.00,
    val formattedDeliveryFee: String = formatDeliveryFee(deliveryFee),
    val isOpen: Boolean = true,
    val openingTime: String = "TODO CHECK IT",
    val closingTime: String = "",
    val openingDays: List<String> = emptyList(),
    val raterCount: Long = 5000,
    val goodRate: Long = 5000,
    val badRate: Long = 0,
    val orderCount: Long = 5000,
    val sponsored: Boolean = false,
    val isRestaurant: Boolean = true,
) {

}



