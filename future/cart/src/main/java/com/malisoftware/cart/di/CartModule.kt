package com.future.cart.di

import com.malisoftware.cart.navigation.CartApi
import com.malisoftware.cart.navigation.CartNav
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CartModule {

    @Provides
    fun provideCartApi(): CartApi {
        return CartNav()
    }

}