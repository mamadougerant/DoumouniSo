package com.malisoftware.restaurant.uistates

import com.malisoftware.model.BusinessData
import com.malisoftware.model.CategoryData
import kotlinx.coroutines.flow.MutableStateFlow

data class RestaurantVmState (
        val loading: Boolean = true,
        val restaurantList: List<BusinessData> = emptyList(),
        val popularRestaurantList: List<BusinessData> = emptyList(),
        val promotionRestaurantList: List<BusinessData> = emptyList(),
        val restaurantCategoryList: List<CategoryData> = emptyList(),
        val sponsorRestaurantList: List<BusinessData> = emptyList(),
        val filterList: List<String> = emptyList(),
        val minPrice: Double = 0.0,
        val maxPrice: Double = 0.0,
        val minRating: Double = 0.0,
        val maxRating: Double = 0.0,
        val minDeliveryTime: Int = 0,
        val maxDeliveryTime: Int = 0,
        val minDeliveryFee: Double = 0.0,
        val maxDeliveryFee: Double = 0.0,
        val openSheet: Boolean = true ,
        val restaurantById: BusinessData? = null,
)