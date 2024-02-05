package com.malisoftware.local.local

import com.malisoftware.model.format.formatPrice

data class ItemsEntity(
    val itemId: Int = 0,
    val title: String = "title",
    val price: Double = 1110.0,
    val description: String = "description",
    val feedback: String = "",
    val imageUrl: String = "",
    val promotion: String = "10% reduction",
    var quantity: Int = 0,
    val category: String = "category",
    val rate: Double = 3.9,
    val raterCount: Long = 5000,
    val goodRate: Long = 5000,
    val badRate: Long = 0,
    val verified: Boolean = false,
    val formattedPrice: String = formatPrice(price)
)
