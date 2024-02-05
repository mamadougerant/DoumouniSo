package com.future.profile.di

import com.future.orders.navigation.OrderApi
import com.future.orders.navigation.OrderNav
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object OrderModule {

    @Provides
    fun provideOrderApi(): OrderApi {
        return OrderNav()
    }

}