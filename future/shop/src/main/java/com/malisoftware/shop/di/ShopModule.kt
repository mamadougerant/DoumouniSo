package com.future.shop.di

import com.malisoftware.shop.navigation.ShopApi
import com.malisoftware.shop.navigation.ShopNav
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object ShopModule {

    @Provides
    fun provideShopApi(): ShopApi {
        return ShopNav()
    }

}