package com.malisoftware.local.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.malisoftware.local.local.UserFavoritesEntity

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM userfavoritesentity")
    fun getFavorite() : LiveData<List<UserFavoritesEntity>>

    @Query("SELECT * FROM userfavoritesentity WHERE id = :id")
    fun getFavoriteById(id: Long) : LiveData<UserFavoritesEntity>

    @Query("DELETE FROM userfavoritesentity WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Delete
    suspend fun delete(userFavoritesEntity: UserFavoritesEntity)

    @Query("DELETE FROM userfavoritesentity")
    suspend fun deleteAll()

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insert(userFavoritesEntity: UserFavoritesEntity)

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertAll(userFavoritesEntity: List<UserFavoritesEntity>)
}