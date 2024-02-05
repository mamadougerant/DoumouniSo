package com.malisoftware.backend.di

import com.malisoftware.backend.dataUseCase.DataUseCase
import com.malisoftware.backend.remote.DataApi
import com.data.backend.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    fun provideDataApi(): DataApi {
        return Repository()
    }

    @Provides
    fun provideDataUseCase(dataApi: DataApi): DataUseCase {
        return DataUseCase(dataApi)
    }

}