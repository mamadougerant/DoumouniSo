package com.malisoftware.local.db


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.malisoftware.local.converter.ItemsTypeConverter
import com.malisoftware.local.converter.StringListConverter
import com.malisoftware.local.dao.AddressDao
import com.malisoftware.local.dao.FavoriteDao
import com.malisoftware.local.dao.OrderDao
import com.malisoftware.local.dao.OrderItemDao
import com.malisoftware.local.dao.RecentlyViewedDao
import com.malisoftware.local.local.AddressEntity
import com.malisoftware.local.local.ItemOrderEntity
import com.malisoftware.local.local.ItemsEntity
import com.malisoftware.local.local.RecentlyViewedEntity
import com.malisoftware.local.local.UserFavoritesEntity


@TypeConverters(value = [ItemsTypeConverter::class, StringListConverter::class])
@Database(entities = [
    ItemOrderEntity::class,
    UserFavoritesEntity::class,
    RecentlyViewedEntity::class,
    AddressEntity::class,
    ItemsEntity::class
                     ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun orderDao(): OrderDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun recentlyViewedDao(): RecentlyViewedDao
    abstract fun address(): AddressDao
    abstract fun orderItemDao(): OrderItemDao
}