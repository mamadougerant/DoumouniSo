package com.future.profile.di

import com.future.profile.navigation.ProfileApi
import com.future.profile.navigation.ProfileNav
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProfileModule {

    @Provides
    fun provideCartApi(): ProfileApi {
        return ProfileNav()
    }

}