package com.malisoftware.doumouniso.navigation

import com.future.orders.navigation.OrderApi
import com.future.profile.navigation.ProfileApi
import com.malisoftware.cart.navigation.CartApi
import com.malisoftware.home.navigation.HomeApi
import com.malisoftware.restaurant.navigation.RestaurantApi
import com.malisoftware.shop.navigation.ShopApi

data class NavigationProvider (
    val homeApi: HomeApi,
    val restaurantApi: RestaurantApi,
    val shopApi: ShopApi,
    val cartApi: CartApi,
    val profileApi: ProfileApi,
    val orderApi: OrderApi
)