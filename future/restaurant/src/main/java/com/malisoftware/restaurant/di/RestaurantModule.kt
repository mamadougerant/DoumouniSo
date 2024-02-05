package com.future.restaurant.di

import com.malisoftware.restaurant.navigation.RestaurantApi
import com.malisoftware.restaurant.navigation.RestaurantNav
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RestaurantModule {

    @Provides
    fun provideRestaurantApi(): RestaurantApi {
        return RestaurantNav()
    }

}