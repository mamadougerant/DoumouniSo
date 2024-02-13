package com.malisoftware.local.di


import android.content.Context
import androidx.room.Room
import com.malisoftware.local.db.AppDatabase
import com.malisoftware.local.reamlModel.RealmBusiness
import com.malisoftware.local.reamlModel.RealmItemOrder
import com.malisoftware.local.reamlModel.RealmItems
import com.malisoftware.local.repository.LocalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
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
        appDatabase: AppDatabase,
        realm: Realm
    ): LocalRepository {
        return LocalRepository(appDatabase, realm)
    }

    @Provides
    @Singleton
    fun provideRealmDB() : Realm {
        return Realm.open(
            configuration = RealmConfiguration.Builder(
                schema = setOf(
                    RealmBusiness::class,
                    RealmItems::class,
                    RealmItemOrder::class
                )
            )
                .schemaVersion(2)
                .build()

        )
    }
}