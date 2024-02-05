package com.malisoftware.local.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.malisoftware.local.local.AddressEntity

@Dao
interface AddressDao {
    @Query("SELECT * FROM addressentity")
    fun getAddress() : LiveData<List<AddressEntity>>

    @Query("SELECT * FROM addressentity WHERE id = :id")
    fun getAddressById(id: Long) : LiveData<AddressEntity>

    @Query("DELETE FROM addressentity WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Delete
    suspend fun delete(addressEntity: AddressEntity)

    @Query("DELETE FROM addressentity")
    suspend fun deleteAll()

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insert(addressEntity: AddressEntity)

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertAll(addressEntity: List<AddressEntity>)
}
