package com.future.home.di

import com.malisoftware.home.navigation.HomeApi
import com.malisoftware.home.navigation.HomeNav
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    fun provideHomeApi(): HomeApi {
        return HomeNav()
    }

}