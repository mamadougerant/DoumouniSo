package com.malisoftware.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.malisoftware.local.local.ItemOrderEntity
import com.malisoftware.local.local.ItemsEntity


@Dao
interface OrderItemDao {

    @Query("SELECT * FROM itemsentity WHERE restaurantId = :restaurantId")
    fun getAllOrderByRestaurantId(restaurantId: String): LiveData<List<ItemsEntity>>

    @Query("DELETE FROM itemsentity WHERE itemId = :itemId")
    suspend fun deleteByItemId(itemId: String)

    @Delete
    suspend fun deleteItem(item: ItemsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(itemsEntity: ItemsEntity)

    @Query("DELETE FROM itemsentity WHERE restaurantId = :restaurantId")
    suspend fun deleteByRestaurantId(restaurantId: String)

}