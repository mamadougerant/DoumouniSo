package com.doumounidron.DoumouniDron.di

import com.future.orders.navigation.OrderApi
import com.future.profile.navigation.ProfileApi
import com.malisoftware.cart.navigation.CartApi
import com.malisoftware.doumouniso.navigation.NavigationProvider
import com.malisoftware.home.navigation.HomeApi
import com.malisoftware.restaurant.navigation.RestaurantApi
import com.malisoftware.shop.navigation.ShopApi

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideNavProvider(
        homeApi: HomeApi,
        restaurantApi: RestaurantApi,
        shopApi: ShopApi,
        cartApi: CartApi,
        profileApi: ProfileApi,
        orderApi: OrderApi
    ): NavigationProvider {
        return NavigationProvider(
            homeApi = homeApi,
            restaurantApi = restaurantApi,
            shopApi = shopApi,
            cartApi = cartApi,
            profileApi = profileApi,
            orderApi = orderApi
        )
    }


}