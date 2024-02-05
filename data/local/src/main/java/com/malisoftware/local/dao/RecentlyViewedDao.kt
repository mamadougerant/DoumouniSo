package com.malisoftware.local.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.malisoftware.local.local.RecentlyViewedEntity

@Dao
interface RecentlyViewedDao {

    @Query("SELECT * FROM recentlyviewedentity")
    fun getRecentlyViewed() : LiveData<List<RecentlyViewedEntity>>

    @Query("SELECT * FROM recentlyviewedentity WHERE id = :id")
    fun getRecentlyViewedById(id: Long) : LiveData<RecentlyViewedEntity>

    @Query("DELETE FROM recentlyviewedentity WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Delete
    suspend fun delete(recentlyViewedEntity: RecentlyViewedEntity)

    @Query("DELETE FROM recentlyviewedentity")
    suspend fun deleteAll()

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insert(recentlyViewedEntity: RecentlyViewedEntity)

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertAll(recentlyViewedEntity: List<RecentlyViewedEntity>)
}