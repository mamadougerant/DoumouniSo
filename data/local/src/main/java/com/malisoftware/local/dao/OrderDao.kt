package com.malisoftware.local.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.malisoftware.local.local.ItemOrderEntity

@Dao
interface OrderDao {

    @Query("SELECT * FROM itemorderentity")
    fun getAll(): LiveData<List<ItemOrderEntity>>

    @Query("SELECT COUNT(*) FROM itemorderentity")
    fun orderCount(): LiveData<Int>

    @Query("SELECT * FROM itemorderentity WHERE id = :id")
    fun getOrderById(id: String): LiveData<ItemOrderEntity>

    @Query("DELETE FROM itemorderentity WHERE id = :id")
    suspend fun deleteById(id: String)

    @Delete
    suspend fun delete(itemOrderEntity: ItemOrderEntity)

    @Query("DELETE FROM itemorderentity")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(itemOrderEntity: ItemOrderEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(itemOrderEntity: ItemOrderEntity)

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertAll(itemOrderEntity: List<ItemOrderEntity>)
}