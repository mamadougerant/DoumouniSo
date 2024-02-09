package com.malisoftware.local.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.malisoftware.local.local.BusinessEntity
import com.malisoftware.local.local.UserFavoritesEntity

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM userfavoritesentity")
    fun getFavorite() : LiveData<List<UserFavoritesEntity>>

    @Query("SELECT * FROM userfavoritesentity WHERE primaryKey = :id")
    fun getFavoriteById(id: String) : LiveData<UserFavoritesEntity>

    @Query("DELETE FROM userfavoritesentity WHERE primaryKey = :id")
    suspend fun deleteById(id: String)

    @Delete
    suspend fun delete(userFavoritesEntity: UserFavoritesEntity)

    @Query("DELETE FROM userfavoritesentity")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userFavoritesEntity: UserFavoritesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userFavoritesEntity: List<UserFavoritesEntity>)
}