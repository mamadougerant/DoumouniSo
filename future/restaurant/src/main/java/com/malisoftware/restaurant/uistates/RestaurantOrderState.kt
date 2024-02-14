package com.malisoftware.restaurant.uistates

import com.malisoftware.model.BusinessData
import com.malisoftware.model.BusinessItems
import com.malisoftware.model.Items
import com.malisoftware.model.ItemsList

data class RestaurantOrderState (
        val loading: Boolean = false,
        val restaurantItems: List<ItemsList> = emptyList(),
        val itemsInPromotion: List<Items> = emptyList(),
        val restaurantsItems: List<BusinessItems> = emptyList(),
        val searchResults: List<Items> = emptyList(),
        val restaurantById: BusinessData? = null,

)