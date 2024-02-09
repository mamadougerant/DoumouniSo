package com.malisoftware.local.di


import android.content.Context
import androidx.room.Room
import com.malisoftware.local.db.AppDatabase
import com.malisoftware.local.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    @Singleton
    fun provideRoomDB(
        @ApplicationContext applicationContext: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "order-database9"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(
        appDatabase: AppDatabase
    ): LocalRepository {
        return LocalRepository(appDatabase)
    }
}